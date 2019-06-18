package com.edidevteste.instagramclone.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.ViewGroup;

import com.edidevteste.instagramclone.Fragment.InicioFragment;
import com.edidevteste.instagramclone.Fragment.UsuariosExistentesFragment;
import com.edidevteste.instagramclone.R;

import java.util.HashMap;

public class TabAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private int[] TAB_TITLES;
    private int[] icones = new int[]{R.drawable.ic_home_black, R.drawable.ic_people_black};
    private HashMap<Integer, Fragment> fragmentUtilizados;

    public TabAdapter(FragmentManager fm, Context context, int[] tabs) {
        super(fm);
        mContext = context;
        TAB_TITLES = tabs;
        fragmentUtilizados = new HashMap<>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new InicioFragment();
                fragmentUtilizados.put(position, fragment);
                break;
            case 1:
                fragment = new UsuariosExistentesFragment();
                fragmentUtilizados.put(position, fragment);
                break;
            default:
                return null;
        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        fragmentUtilizados.remove(position);
    }

    public Fragment getFragment(Integer indice){
        return fragmentUtilizados.get(indice);
    }

    /*@Override
    public CharSequence getPageTitle(int position) {
        *//*String titulo = mContext.getResources().getString(TAB_TITLES[position]);
        Drawable drawable = ContextCompat.getDrawable(mContext, icones[position]);

        SpannableStringBuilder spannableString = new SpannableStringBuilder("Page " + position);

        try{
            drawable.setBounds(5, 5, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

            ImageSpan imageSpan = new ImageSpan(drawable, DynamicDrawableSpan.ALIGN_BASELINE);

            spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }catch (Exception e){
            Log.e("ErrorIcon", "Error mostrar icone, " + e.getMessage());
        }
        return spannableString;*//*
        return mContext.getResources().getString(TAB_TITLES[position]);
    }*/

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}
