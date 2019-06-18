package com.edidevteste.instagramclone.Fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edidevteste.instagramclone.R;

public class InicioFragment extends Fragment {

    public static InicioFragment newInstance(int index) {
        InicioFragment fragment = new InicioFragment();
        //Bundle bundle = new Bundle();
        //bundle.putInt(ARG_SECTION_NUMBER, index);
        //fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }
}