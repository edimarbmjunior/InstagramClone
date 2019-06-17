package com.edidevteste.instagramclone.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.edidevteste.instagramclone.R;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializar Parse
        //inicializaParse();
        //brincandoParseQuery();
        //trabalhandoComUsuario();
        inicializar();
    }

    private void inicializaParse(){
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    private void brincandoParseQuery(){
        //Modos de salvar dados usando Parse(O parse usa o NoSql(MongoDB) para salvar dados)
        /*ParseObject pontuacao = new ParseObject("Pontuacao");
        pontuacao.put("nome", "Maria");
        pontuacao.put("pontos", 1000);

        //pontuacao.saveInBackground();
        //Ou
        pontuacao.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    //Sem erro
                    Log.i("SalvarPontos", "Dados Salvos");
                }else{
                    //Com erro
                    Log.i("SalvarPontos", "Dados não salvos");
                }
            }
        });*/

        //Modo de consulta de dados no Parse
        /*ParseQuery<ParseObject> consulta = ParseQuery.getQuery("Pontuacao");
        consulta.getInBackground("pmdQR9BLVk", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e==null){
                    object.put("nome", "Juan");
                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e == null){
                                //Sem erro
                                Log.i("SalvarPontos", "Dados Alterados");
                            }else{
                                //Com erro
                                Log.i("SalvarPontos", "Dados não Alterados");
                            }
                        }
                    });
                }else{
                    Log.i("ConsultaPontos", "Error na consulta, " + e);
                }
            }
        });*/

        //ParseQuery<ParseObject> filtro = ParseQuery.getQuery("Pontuacao");
        //Aplicação de filtro
        //filtro.whereGreaterThan("pontos", 800);
        //filtro.whereGreaterThanOrEqualTo("pontos", 500);
        //filtro.whereLessThan("pontos", 500);
        //filtro.whereLessThanOrEqualTo("pontos", 500);
        //filtro.whereEndsWith("nome", "ia");
        //filtro.addAscendingOrder("pontos");
        //filtro.setLimit(2);

        /*filtro.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    Log.i("ListarDados", "Sucesso na listagem dos obejtos, tamanho " + objects.size());

                    for(ParseObject object : objects){
                        Log.i("ListarDados", "Objeto: " + object.get("nome") + "/" + object.get("pontos"));
                    }
                }else{
                    Log.i("ListarDados", "Error na consulta, " + e);
                }
            }
        });*/
    }

    private void trabalhandoComUsuario(){
        //Comço do cadastro do usuario
        /*ParseUser usuario = new ParseUser();
        usuario.setUsername("Edimar");
        usuario.setPassword("123456");
        usuario.setEmail("edimar@gmail.com");

        //Cadastrar Usuário
        usuario.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    Log.i("Usuario", "Cadsatro de usuario efetuado");
                }else{
                    Log.i("Usuario", "Cadsatro de usuario não efetuado. Error - " + e);
                }
            }
        });*/

        //Delosgar o usuario
        //ParseUser.logOut();

        //Verificar Usuário Logado
        /*if(ParseUser.getCurrentUser() != null){//Usuario logado
            Log.i("Usuario", "Usuario logado");
        }else{
            Log.i("Usuario", "Usuario não logado");
        }*/

        //Fazer login
        /*ParseUser.logInInBackground("Edimar", "123456", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e==null){
                    Log.i("Usuario", "Usuario logado");
                }else{
                    Log.i("Usuario", "Usuario não logado. Error - " + e);
                }
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.itemMenuSair:
                Log.i("Menu", "Selecionou Sair");
                sair();
                return true;
            case R.id.itemMenuConfiguracoes:
                Log.i("Menu", "Selecionou Configurações");
                return true;
            case R.id.itemMenuComprtilhar:
                Log.i("Menu", "Selecionou Compartilhar");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void inicializar(){
        toolbarPrincipal = findViewById(R.id.toolbarPrincipal);
        toolbarPrincipal.setLogo(R.drawable.instagramlogo);

        setSupportActionBar(toolbarPrincipal);
    }

    private void sair(){
        ParseUser.logOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}
