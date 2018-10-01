package com.ioasys.italo.appempresas.RecycleViewEmpresas;

import android.widget.ImageView;

public class Empresa {
    private String mNomeEmpresa;
    private String mNegocio;
    private String mLocalidade;
    private String mDescription;
    private String mUrlImage;

    public Empresa(String mNomeEmpresa, String mNegocio, String mLocalidade, String mDescription, String mUrlImage) {
        this.mNomeEmpresa = mNomeEmpresa;
        this.mNegocio = mNegocio;
        this.mLocalidade = mLocalidade;
        this.mDescription = mDescription;
        this.mUrlImage = mUrlImage;
    }

    public String getmNomeEmpresa() {
        return mNomeEmpresa;
    }

    public String getmNegocio() {
        return mNegocio;
    }

    public String getmLocalidade() {
        return mLocalidade;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmUrlImage() {
        return mUrlImage;
    }

}
