package com.edidevteste.instagramclone.Fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.edidevteste.instagramclone.Adapter.InicioAdapter;
import com.edidevteste.instagramclone.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class InicioFragment extends Fragment {

    private ListView listViewInicioImagens;
    private ArrayList<ParseObject> mListaPostagens;
    private ArrayAdapter<ParseObject> adapter;
    private ParseQuery<ParseObject> mQuery;

    public static InicioFragment newInstance(int index) {
        InicioFragment fragment = new InicioFragment();
        //Bundle bundle = new Bundle();
        //bundle.putInt(ARG_SECTION_NUMBER, index);
        //fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        listViewInicioImagens = view.findViewById(R.id.listViewInicioImagens);

        mListaPostagens = new ArrayList<>();
        adapter = new InicioAdapter(getActivity(), mListaPostagens);
        listViewInicioImagens.setAdapter(adapter);

        //Recupera as imagens
        recuperaPostagens();

        return view;
    }

    private void recuperaPostagens(){
        mQuery = ParseQuery.getQuery("Imagem");
        mQuery.whereEqualTo("keyUser", ParseUser.getCurrentUser().getObjectId());
        mQuery.orderByDescending("createdAt");

        mQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    Log.e("recuperaImagem", "Error recuperar imagem, coderro:" + e.getCode() + "/ Msg: " + e.getMessage());
                    Toast.makeText(getActivity(), "Error ao recuperar as imagens", Toast.LENGTH_LONG).show();
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

    public void atualizaPostagens(){
        recuperaPostagens();
    }
}