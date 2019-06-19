package com.edidevteste.instagramclone.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.edidevteste.instagramclone.Adapter.TabAdapter;
import com.edidevteste.instagramclone.Fragment.InicioFragment;
import com.edidevteste.instagramclone.R;
import com.edidevteste.instagramclone.Security.SecurityPreferences;
import com.edidevteste.instagramclone.Util.UtilContantes;
import com.edidevteste.instagramclone.Util.UtilGenerico;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ProgressCallback;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarPrincipal;
    private ViewPager viewPager;

    private SecurityPreferences sharedPreferences;
    private AlertDialog alertDialog;

    private static final int IMAGE_VIEW_ACTIVITY_REQUEST_CODE = new Integer(UtilContantes.REQUEST_SCOPE_IMAGE_VIEW_ACTIVITY.getColuna1());

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
                compartilharFoto();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void inicializar(){
        toolbarPrincipal = findViewById(R.id.toolbarPrincipal);
        toolbarPrincipal.setLogo(R.drawable.instagramlogo);
        setSupportActionBar(toolbarPrincipal);

        int[] TAB_TITLES = new int[]{R.string.tab_1, R.string.tab_2};

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), this, TAB_TITLES);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(tabAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.getTabAt(0).setIcon(R.drawable.ic_home_black);
        tabs.getTabAt(1).setIcon(R.drawable.ic_people_black);
        tabs.setBackgroundResource(R.color.colorPrimary);
        sharedPreferences = new SecurityPreferences(getApplicationContext());
    }

    private void sair(){
        ParseUser.logOut();
        ArrayList<String> listaRemover = new ArrayList<>();
        listaRemover.add(UtilContantes.USUARIO_DADOS.getColuna1());
        listaRemover.add(UtilContantes.USUARIO_DADOS.getColuna2());
        listaRemover.add(UtilContantes.USUARIO_DADOS.getColuna3());
        listaRemover.add(UtilContantes.USUARIO_DADOS.getColuna4());
        sharedPreferences.removerValoresPreferencesUsuario(listaRemover);
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    private void compartilharFoto(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE_VIEW_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Menu", "Foi escolhida a Foto");

        if(requestCode == IMAGE_VIEW_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null){

            alertDialog = new UtilGenerico().AlertDialog(getApplicationContext());
            alertDialog.show();
            Boolean verificaConexao = UtilGenerico.isConected(getApplicationContext());
            String ObejectIdUser = ParseUser.getCurrentUser().getObjectId();

            Log.i("Menu", "Verificando a conexão com a internet " + verificaConexao);
            Log.i("Menu", "Identificação: " + ObejectIdUser);
            if(verificaConexao && ObejectIdUser!= null){
                //Recupera local do recurso
                Uri localImagemSelecionada = data.getData();

                //Recupera a imagem do local que foi selecionado
                try {
                    Bitmap imagem = MediaStore.Images.Media.getBitmap(getContentResolver(), localImagemSelecionada);

                    //Converter para PNG
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imagem.compress(Bitmap.CompressFormat.PNG, 100, stream);

                    //Cria um array de bytes
                    byte[] bytesArrays = stream.toByteArray();

                    //Criar arquivo no formato do Parse
                    SimpleDateFormat dateFormat = new SimpleDateFormat("ddmmaaaahhmmss");
                    String nomeImagem = dateFormat.format(new Date());
                    final ParseFile parseFile = new ParseFile(nomeImagem+"imagem.png", bytesArrays);

                    parseFile.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e!=null){
                                alertDialog.dismiss();
                                Log.e("Menu", "Error ao postar imagem(ParseFile), Coderro: " + e.getCode() + "/ Msg: " + e.getMessage());
                                Toast.makeText(getApplicationContext(), "Erro ao postar a imagem! Tente novamente!", Toast.LENGTH_LONG).show();
                            }else{
                                //Monta o objeto Parse para salvar na imagem
                                ParseObject parseObject = new ParseObject("Imagem");
                                parseObject.put("keyUser", ParseUser.getCurrentUser().getObjectId());
                                parseObject.put("imagem", parseFile);

                                //Salvar dados
                                parseObject.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if(e==null){
                                            alertDialog.dismiss();
                                            Toast.makeText(getApplicationContext(), "Sua imagem foi postada", Toast.LENGTH_LONG).show();

                                            //Atualizar o Fragment
                                            TabAdapter tabAdapterNovo = (TabAdapter) viewPager.getAdapter();
                                            InicioFragment inicioFragment = (InicioFragment) tabAdapterNovo.getFragment(0);
                                            inicioFragment.atualizaPostagens();
                                        }else {
                                            alertDialog.dismiss();
                                            Log.e("Menu", "Error ao postar imagem(ParseObject), Coderro: " + e.getCode() + "/ Msg: " + e.getMessage());
                                            Toast.makeText(getApplicationContext(), "Erro ao postar a imagem! Tente novamente!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        }
                    });

                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Erro no processamento da imagem! Tente Novamente!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                    Log.e("Menu", "Erro no processamento da imagem, " + e);
                }
            }else{
                Toast.makeText(getApplicationContext(), "Sem conexão com a internet!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
