package com.edidevteste.instagramclone.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edidevteste.instagramclone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsuariosExistentesFragment extends Fragment {


    public UsuariosExistentesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_usuarios_existentes, container, false);
    }

}
