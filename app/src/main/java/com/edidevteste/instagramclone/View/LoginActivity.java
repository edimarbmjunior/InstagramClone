package com.edidevteste.instagramclone.View;

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
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText textViewLoginNome;
    private EditText textViewLoginSenha;
    private TextView textViewLoginCrieConta;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        if(ParseUser.getCurrentUser()!=null){
            Toast.makeText(getApplicationContext(), "Usuário logado", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    private void setListeners(){
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificaLogin();
            }
        });
    }

    private void verificaLogin(){
        String usuarioNome = textViewLoginNome.getText().toString();
        String usuarioSenha = textViewLoginSenha.getText().toString();

        if(usuarioNome.isEmpty() || usuarioSenha.isEmpty()){
            Toast.makeText(getApplicationContext(), "Preencha corretamente os dados.", Toast.LENGTH_LONG).show();
        }else{
            ParseUser.logInInBackground(usuarioNome, usuarioSenha, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e==null){
                        Toast.makeText(getApplicationContext(), "Sucesso ao logar.", Toast.LENGTH_LONG).show();
                        abrirMain();
                    }else{
                        Toast.makeText(getApplicationContext(), "Error ao Logar.", Toast.LENGTH_LONG).show();
                        Log.i("ErrorLogin", "Error(Logar)-Code: >" + e.getCode());
                        Log.i("ErrorLogin", "Error(Logar)-Message: >" + e.getMessage());
                    }
                }
            });
        }
    }

    private void abrirMain(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
