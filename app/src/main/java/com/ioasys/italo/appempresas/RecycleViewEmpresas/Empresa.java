package com.ioasys.italo.appempresas.RecycleViewEmpresas;

import android.widget.ImageView;

public class Empresa {
  //  private ImageView mEmpresaImagem; //picture?
    private String mNomeEmpresa;
    private String mNegocio;
    private String mLocalidade;

    public Empresa(//ImageView mEmpresaImagem,
                    String mNomeEmpresa, String mNegocio, String mLocalidade) {
//        this.mEmpresaImagem = mEmpresaImagem;
        this.mNomeEmpresa = mNomeEmpresa;
        this.mNegocio = mNegocio;
        this.mLocalidade = mLocalidade;
    }

    public void setmEmpresaImagem(ImageView mEmpresaImagem) {
//        this.mEmpresaImagem = mEmpresaImagem;
    }

    public void setmNomeEmpresa(String mNomeEmpresa) {
        this.mNomeEmpresa = mNomeEmpresa;
    }

    public void setmNegocio(String mNegocio) {
        this.mNegocio = mNegocio;
    }

    public void setmLocalidade(String mLocalidade) {
        this.mLocalidade = mLocalidade;
    }

//    public ImageView getmEmpresaImagem() {
//        return mEmpresaImagem;
//    }

    public String getmNomeEmpresa() {
        return mNomeEmpresa;
    }

    public String getmNegocio() {
        return mNegocio;
    }

    public String getmLocalidade() {
        return mLocalidade;
    }
}
