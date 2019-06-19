package com.edidevteste.instagramclone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edidevteste.instagramclone.R;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsuariosExistentetesAdapter extends ArrayAdapter<ParseUser> {

    private Context mContext;
    private ArrayList<ParseUser> mArrayListUsers;

    public UsuariosExistentetesAdapter(Context context, ArrayList<ParseUser> objects) {
        super(context, 0, objects);
        mContext = context;
        mArrayListUsers = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view==null){
            //inicializa a view para a montagem do layout
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);

            //monta view a partir de um xml
            view = inflater.inflate(R.layout.lista_usuarios, parent, false);
        }

        if(mArrayListUsers.size() > 0){
            //recupera componente da tela
            TextView textViewListaUsuario = view.findViewById(R.id.textViewListaUsuario);

            ParseUser parseUser = mArrayListUsers.get(position);

            //Metodo especifico para recuperar arquivo
            //parseObject.getParseFile("imagem");

            String nameUser = parseUser.getUsername();

            textViewListaUsuario.setText(nameUser);
        }

        return view;
    }
}
