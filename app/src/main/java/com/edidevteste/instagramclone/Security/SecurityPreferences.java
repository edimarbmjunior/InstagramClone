package com.edidevteste.instagramclone.Security;

import android.content.Context;
import android.content.SharedPreferences;

import com.edidevteste.instagramclone.Util.UtilContantes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SecurityPreferences {

    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    public SecurityPreferences(Context contextParametro){
        mContext = contextParametro;
        mSharedPreferences = mContext.getSharedPreferences(UtilContantes.SECURITY_PREFERENCES.getColuna1(), Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }

    public void salvarValorPreferences(String key, String dado){
        editor.putString(key, dado);
        editor.commit();
    }

    public void salvarValoresPreferences(HashMap<String, String> dados){
        for (Iterator<Map.Entry<String, String>> dado = dados.entrySet().iterator(); dado.hasNext();) {
            Map.Entry<String, String> valores = dado.next();
            if(valores.getValue().length()>0){
                editor.putString(valores.getKey(), valores.getValue());
                editor.commit();
            }
        }
    }

    public String recuperarValorUnicoPrefences(String key){
        return mSharedPreferences.getString(key, "");
    }

    public HashMap<String, String> recuperarValoresUnicoPrefences(List<String> keys){
        HashMap<String, String> retorno = new HashMap();

        for (String key : keys){
            String valor = mSharedPreferences.getString(key, "");
            if(valor.length()>1){
                retorno.put(key, valor);
            }
        }
        return retorno;
    }

    public void removerValorPreferencesUsuario(String key){
        editor.remove(key);
        editor.commit();
    }

    public void removerValoresPreferencesUsuario(ArrayList<String> keys){
        for (String key : keys){
            editor.remove(key);
            editor.commit();
        }
    }
}
