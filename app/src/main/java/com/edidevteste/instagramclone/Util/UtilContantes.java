package com.edidevteste.instagramclone.Util;

public enum UtilContantes {

    USUARIO_DADOS ("USUARIO_ID", "USUARIO_NOME", "USUARIO_EMAIL", "USUARIO_SENHA"),
    SECURITY_PREFERENCES ("INSTAGRAMCLONEFUNCTIONAL"),
    PUT_EXTRA_FEED ("USER_ID", "USER_NOME"),
    REQUEST_SCOPE_PERMISSOES("1"),
    REQUEST_SCOPE_IMAGE_VIEW_ACTIVITY("101");

    private String coluna1;
    private String coluna2;
    private String coluna3;
    private String coluna4;

    private UtilContantes(String coluna1) {
        this.coluna1 = coluna1;
    }

    private UtilContantes(String coluna1, String coluna2) {
        this.coluna1 = coluna1;
        this.coluna2 = coluna2;
    }

    private UtilContantes(String coluna1, String coluna2, String coluna3) {
        this.coluna1 = coluna1;
        this.coluna2 = coluna2;
        this.coluna3 = coluna3;
    }

    private UtilContantes(String coluna1, String coluna2, String coluna3, String coluna4) {
        this.coluna1 = coluna1;
        this.coluna2 = coluna2;
        this.coluna3 = coluna3;
        this.coluna4 = coluna4;
    }

    public String getColuna1() {
        return coluna1;
    }
    public String getColuna2() {
        return coluna2;
    }
    public String getColuna3() {
        return coluna3;
    }
    public String getColuna4() {
        return coluna4;
    }
}
