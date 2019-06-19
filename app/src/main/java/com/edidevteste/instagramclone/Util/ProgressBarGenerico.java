package com.edidevteste.instagramclone.Util;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

public class ProgressBarGenerico extends AsyncTask<Void, Integer, String> {

    private Context mContext;
    private ProgressBar progressBar;

    public ProgressBarGenerico(Context ctx, ProgressBar progressBar) {
        this.mContext = ctx;
        this.progressBar = progressBar;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return null;
    }
}
