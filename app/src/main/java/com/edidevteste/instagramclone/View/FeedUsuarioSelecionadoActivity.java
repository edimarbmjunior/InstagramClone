package com.edidevteste.instagramclone.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.edidevteste.instagramclone.R;
import com.edidevteste.instagramclone.Util.UtilContantes;

public class FeedUsuarioSelecionadoActivity extends AppCompatActivity {

    private Toolbar mToolbarFeedUsuario;
    private ListView mListViewFeedUsuario;

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

    }

}
