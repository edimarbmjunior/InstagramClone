package com.edidevteste.instagramclone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.edidevteste.instagramclone.R;
import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InicioAdapter extends ArrayAdapter<ParseObject> {

    private Context mContext;
    private ArrayList<ParseObject> mListaPostagens;

    public InicioAdapter(Context context, ArrayList<ParseObject> objects) {
        super(context, 0, objects);
        mContext = context;
        mListaPostagens = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view==null){
            //inicializa a view para a montagem do layout
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);

            //monta view a partir de um xml
            view = inflater.inflate(R.layout.lista_postagem, parent, false);
        }

        if(mListaPostagens.size() > 0){
            //recupera componente da tela
            ImageView imageViewListaPostagem = view.findViewById(R.id.imageViewListaPostagem);

            ParseObject parseObject = mListaPostagens.get(position);

            //Metodo especifico para recuperar arquivo
            //parseObject.getParseFile("imagem");

            Picasso.get()
                    .load(parseObject.getParseFile("imagem").getUrl())
                    .fit()
                    .into(imageViewListaPostagem);
        }

        return view;
    }
}
