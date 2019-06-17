package com.edidevteste.instagramclone.Util;

import java.util.HashMap;

public class ParsesErrorUtil {

    private HashMap<Integer, String> listaMsg;

    public ParsesErrorUtil() {
        this.listaMsg = new HashMap();
        //Visualizar possiveis códigos no link "https://parseplatform.org/Parse-SDK-Android/api/constant-values.html"
        this.listaMsg.put(202, "Usuário já existe! Escolha outro nome de usuário!");
        this.listaMsg.put(201, "Senha inválida, preencha a senha corretamente!");
    }

    public String getErroParse(Integer codErro){
        return this.listaMsg.get(codErro);
    }
}
