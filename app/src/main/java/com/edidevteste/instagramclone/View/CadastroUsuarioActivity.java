package com.edidevteste.instagramclone.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edidevteste.instagramclone.R;
import com.edidevteste.instagramclone.Security.Base64Custom;
import com.edidevteste.instagramclone.Security.SecurityPreferences;
import com.edidevteste.instagramclone.Util.ParsesErrorUtil;
import com.edidevteste.instagramclone.Util.UtilContantes;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.HashMap;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText textViewCadastroNome;
    private EditText textViewCadastroEmail;
    private EditText textViewCadastroSenha;
    private TextView textViewLoginCrieConta;
    private Button buttonCadastrar;
    private String msgErro = "";

    private SecurityPreferences securityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        inicializar();
        setLiteners();
    }

    private void inicializar(){
        textViewCadastroNome = findViewById(R.id.textViewCadastroNome);
        textViewCadastroEmail = findViewById(R.id.textViewCadastroEmail);
        textViewCadastroSenha = findViewById(R.id.textViewCadastroSenha);
        textViewLoginCrieConta = findViewById(R.id.textViewLoginCrieConta);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);

        securityPreferences = new SecurityPreferences(getApplicationContext());
    }

    private void setLiteners(){
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgErro = "";
                if(validacao()){
                    cadastrarUsuario();
                }else{
                    Toast.makeText(getApplicationContext(), msgErro, Toast.LENGTH_LONG).show();
                }
            }
        });

        textViewLoginCrieConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLoginUsuario();
            }
        });
    }

    public void abrirLoginUsuario(){
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    private void cadastrarUsuario(){
        //Cria o objeto usuario
        ParseUser usuario = new ParseUser();
        usuario.setUsername(textViewCadastroNome.getText().toString());
        usuario.setEmail(textViewCadastroEmail.getText().toString());
        usuario.setPassword(Base64Custom.CodificaTo64(textViewCadastroSenha.getText().toString()));

        usuario.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    Toast.makeText(getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_LONG).show();

                    HashMap<String, String> dadosSalvar = new HashMap<>();
                    String Session = ParseUser.getCurrentUser().getObjectId();
                    dadosSalvar.put(UtilContantes.USUARIO_DADOS.getColuna1(), Session);
                    dadosSalvar.put(UtilContantes.USUARIO_DADOS.getColuna2(), ParseUser.getCurrentUser().getUsername());
                    dadosSalvar.put(UtilContantes.USUARIO_DADOS.getColuna3(), ParseUser.getCurrentUser().getEmail());
                    dadosSalvar.put(UtilContantes.USUARIO_DADOS.getColuna4(), Base64Custom.CodificaTo64(textViewCadastroSenha.getText().toString()));

                    securityPreferences.salvarValoresPreferences(dadosSalvar);

                    ParseUser.logOut();
                    abrirLoginUsuario();
                }else{
                    String msgErroParse = new ParsesErrorUtil().getErroParse(e.getCode());
                    if(msgErroParse!=null&&msgErroParse.length()>1){
                        Toast.makeText(getApplicationContext(), msgErroParse, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Cadastrado com erro - Cod: " +e.getCode() + " / Msg: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private Boolean validacao(){
        if(textViewCadastroNome.getText().toString().isEmpty() ||
                textViewCadastroNome.getText().toString().isEmpty() ||
                textViewCadastroNome.getText().toString().isEmpty()){
            msgErro = "Preencha os campos";
            return false;
        }
        return true;
    }
}
