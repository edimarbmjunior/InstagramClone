package com.edidevteste.instagramclone.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.edidevteste.instagramclone.Adapter.UsuariosExistentetesAdapter;
import com.edidevteste.instagramclone.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsuariosExistentesFragment extends Fragment {

    private ListView mListViewUsuaruisExistentes;
    private ArrayAdapter<ParseUser> arrayAdapterUsers;
    private ParseQuery<ParseUser> mQuery;

    private ArrayList<ParseUser> mArrayListusers;

    public UsuariosExistentesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuarios_existentes, container, false);

        mArrayListusers = new ArrayList<>();
        mListViewUsuaruisExistentes = view.findViewById(R.id.listViewUsuaruisExistentes);
        arrayAdapterUsers = new UsuariosExistentetesAdapter(getActivity(), mArrayListusers);
        mListViewUsuaruisExistentes.setAdapter(arrayAdapterUsers);

        recuperaUsuarios();

        return view;
    }

    private void recuperaUsuarios(){
        mQuery = ParseUser.getQuery();
        mQuery.whereNotEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());
        mQuery.orderByDescending("createdAt");

        mQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e!=null){
                    Log.e("recuperaUsuarios", "Error recuperar usuarios, coderro:" + e.getCode() + "/ Msg: " + e.getMessage());
                    Toast.makeText(getActivity(), "Error ao recuperar os usuarios", Toast.LENGTH_LONG).show();
                }else{
                    mArrayListusers.clear();
                    for(ParseUser parseUser : objects){
                        mArrayListusers.add(parseUser);
                    }
                    arrayAdapterUsers.notifyDataSetChanged();
                }
            }
        });
    }

}
