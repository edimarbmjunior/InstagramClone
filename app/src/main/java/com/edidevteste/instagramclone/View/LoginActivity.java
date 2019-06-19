package com.edidevteste.instagramclone.View;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edidevteste.instagramclone.R;
import com.edidevteste.instagramclone.Security.Base64Custom;
import com.edidevteste.instagramclone.Security.Permissao;
import com.edidevteste.instagramclone.Security.SecurityPreferences;
import com.edidevteste.instagramclone.Util.ParsesErrorUtil;
import com.edidevteste.instagramclone.Util.UtilContantes;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private EditText textViewLoginNome;
    private EditText textViewLoginSenha;
    private TextView textViewLoginCrieConta;
    private Button buttonLogin;

    //Salvar dados na SharedPreference
    SecurityPreferences sharedPreference;

    private String[] permissoesNecessarias = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //RequestCode - Para verificar se a sua activity tem permissão, cada activity de seu request
        Integer requestCode = new Integer(UtilContantes.REQUEST_SCOPE_PERMISSOES.getColuna1());
        Permissao.validaPermissoes(requestCode, this, permissoesNecessarias);

        inicializar();
        setListeners();
    }

    public void abrirCadastroUsuario(View view){
        startActivity(new Intent(getApplicationContext(), CadastroUsuarioActivity.class));
    }

    private void inicializar(){
        textViewLoginNome = findViewById(R.id.textViewLoginNome);
        textViewLoginSenha = findViewById(R.id.textViewLoginSenha);
        textViewLoginCrieConta = findViewById(R.id.textViewLoginCrieConta);
        buttonLogin = findViewById(R.id.buttonLogin);
        sharedPreference = new SecurityPreferences(getApplicationContext());

        String idUsuario = sharedPreference.recuperarValorUnicoPrefences(UtilContantes.USUARIO_DADOS.getColuna1());
        Log.i("Login", "Usuario:" + idUsuario);
        if(idUsuario!=null && idUsuario.length()>0){
            Log.i("Login", "ParseUser.logOut()");
            ParseUser.logOut();
            String usuarioLogin = sharedPreference.recuperarValorUnicoPrefences(UtilContantes.USUARIO_DADOS.getColuna2());
            String senhaLogin   = sharedPreference.recuperarValorUnicoPrefences(UtilContantes.USUARIO_DADOS.getColuna4());
            verificaLogin(usuarioLogin, senhaLogin);
        }
        /*if(ParseUser.getCurrentUser()!=null){
            Toast.makeText(getApplicationContext(), "Usuário logado", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }*/
    }

    private void setListeners(){
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Login", "setListeners ButtonLogin");
                String usuarioNome = textViewLoginNome.getText().toString();
                String usuarioSenha = Base64Custom.CodificaTo64(textViewLoginSenha.getText().toString());
                verificaLogin(usuarioNome, usuarioSenha);
            }
        });
    }

    private void verificaLogin(String usuarioLogin, String usuarioSenha){
        final String[] valores = {usuarioLogin, usuarioSenha};

        if(usuarioLogin.isEmpty() || usuarioSenha.isEmpty()){
            Toast.makeText(getApplicationContext(), "Preencha corretamente os dados.", Toast.LENGTH_LONG).show();
        }else{
            ParseUser.logInInBackground(usuarioLogin, usuarioSenha, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e==null){
                        Toast.makeText(getApplicationContext(), "Sucesso ao logar.", Toast.LENGTH_LONG).show();
                        sharedPreferences(valores[0], valores[1]);
                        abrirMain();
                    }else{
                        String msgErroParse = new ParsesErrorUtil().getErroParse(e.getCode());
                        if(msgErroParse!=null&&msgErroParse.length()>1){
                            Toast.makeText(getApplicationContext(), msgErroParse, Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Error ao Logar - Cod: " +e.getCode() + " / Msg: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        Log.e("ErrorLogin", "Error(Logar)-Code: >" + e.getCode());
                        Log.e("ErrorLogin", "Error(Logar)-Message: >" + e.getMessage());
                    }
                }
            });
        }
    }

    private void sharedPreferences(String usuarioLogin, String usuarioSenha){
        HashMap<String, String> dadosSalvar = new HashMap<>();
        String session = ParseUser.getCurrentUser().getObjectId();
        dadosSalvar.put(UtilContantes.USUARIO_DADOS.getColuna1(), session);
        dadosSalvar.put(UtilContantes.USUARIO_DADOS.getColuna2(), ParseUser.getCurrentUser().getUsername());
        dadosSalvar.put(UtilContantes.USUARIO_DADOS.getColuna3(), usuarioLogin);
        dadosSalvar.put(UtilContantes.USUARIO_DADOS.getColuna4(), usuarioSenha);
        sharedPreference.salvarValoresPreferences(dadosSalvar);
    }

    private void abrirMain(){
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
