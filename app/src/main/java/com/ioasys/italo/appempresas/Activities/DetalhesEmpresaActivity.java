package com.ioasys.italo.appempresas.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.ioasys.italo.appempresas.Glide.GlideApp;
import com.ioasys.italo.appempresas.R;
import com.ioasys.italo.appempresas.RetrofitResources.remote.ApiUtils;

public class DetalhesEmpresaActivity extends AppCompatActivity {

    private TextView mDescricao;
    private ImageView mImagem;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhesempresa);

        Bundle bundle = getIntent().getExtras();

        encontrandoViewsPorId();

        configuracoesToolBar(bundle);

        if (bundle == null) return;

        //exibe a descrição da empresa clicada
        mDescricao.setText(bundle.getString("description"));
        GlideApp.with(this)
                .load(ApiUtils.BASE_IMAGE + bundle.getString("image"))
                .placeholder(getDrawable(android.R.drawable.ic_menu_report_image))
                .into(mImagem);
    }

    public void encontrandoViewsPorId() {
        mDescricao = findViewById(R.id.detalhesEmpresa_descricao_textView);
        mImagem = findViewById(R.id.detalhesempresa_imagem_imageView);
        toolbar = findViewById(R.id.detalhesempresa_toolbar);
    }

    //altera titulo da Toolbar de acordo com nome da empresa e adiciona botao voltar
    public void configuracoesToolBar(Bundle bundle) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar == null) return;

        actionBar.setTitle(bundle.getString("enterprise_name"));
        actionBar.setDisplayHomeAsUpEnabled(true); //adicionando botão voltar
        actionBar.setDisplayShowHomeEnabled(true); //exibindo botão voltar
    }

    //finaliza a atual activity ao pressionar botao voltar
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
