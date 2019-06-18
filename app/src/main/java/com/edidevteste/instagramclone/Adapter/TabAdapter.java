package com.edidevteste.instagramclone.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

import com.edidevteste.instagramclone.Fragment.InicioFragment;
import com.edidevteste.instagramclone.Fragment.UsuariosExistentesFragment;
import com.edidevteste.instagramclone.R;

public class TabAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private int[] TAB_TITLES;
    private int[] icones = new int[]{R.drawable.ic_home_black, R.drawable.ic_people_black};

    public TabAdapter(FragmentManager fm, Context context, int[] tabs) {
        super(fm);
        mContext = context;
        TAB_TITLES = tabs;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;

        switch (i){
            case 0:
                fragment = new InicioFragment();
                break;
            case 1:
                fragment = new UsuariosExistentesFragment();
                break;
            default:
                return null;
        }
        return fragment;
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
