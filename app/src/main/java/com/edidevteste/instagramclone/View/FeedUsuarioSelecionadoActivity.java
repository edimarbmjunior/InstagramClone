package com.edidevteste.instagramclone.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.edidevteste.instagramclone.Adapter.InicioAdapter;
import com.edidevteste.instagramclone.R;
import com.edidevteste.instagramclone.Util.UtilContantes;
import com.edidevteste.instagramclone.Util.UtilGenerico;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class FeedUsuarioSelecionadoActivity extends AppCompatActivity {

    private Toolbar mToolbarFeedUsuario;
    private ListView mListViewFeedUsuario;
    private ArrayAdapter<ParseObject> adapter;
    private ArrayList<ParseObject> mListaPostagens;

    private String mUserId;
    private String mUserNome;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_usuario_selecionado);

        recuperaDadosEnviados();

        mToolbarFeedUsuario = findViewById(R.id.toolbarFeedUsuario);
        mToolbarFeedUsuario.setTitle(mUserNome);
        mToolbarFeedUsuario.setTitleTextColor(R.color.preto);
        mToolbarFeedUsuario.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);

        setSupportActionBar(mToolbarFeedUsuario);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        incializacaoPosterior();
    }

    private void recuperaDadosEnviados(){
        Intent intent = getIntent();
        mUserId = intent.getStringExtra(UtilContantes.PUT_EXTRA_FEED.getColuna1());
        mUserNome = intent.getStringExtra(UtilContantes.PUT_EXTRA_FEED.getColuna2());
    }

    private void incializacaoPosterior(){
        mListViewFeedUsuario = findViewById(R.id.listViewFeedUsuario);
        mListaPostagens = new ArrayList<>();
        adapter = new InicioAdapter(getApplicationContext(), mListaPostagens);
        mListViewFeedUsuario.setAdapter(adapter);

        getPostagens();
    }

    private void getPostagens(){
        //Recupera as postagens
        ParseQuery<ParseObject> query = new ParseQuery<>("Imagem");
        query.whereEqualTo("keyUser", mUserId);
        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    Log.e("recuperaImagem", "Error recuperar imagem, coderro:" + e.getCode() + "/ Msg: " + e.getMessage());
                    UtilGenerico.msgGenericaLong(getApplicationContext(), "Error ao recuperar as imagens do usuário!");
                }else{
                    if(objects.size()>0){
                        mListaPostagens.clear();
                        for (ParseObject parseObject : objects){
                            mListaPostagens.add(parseObject);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
