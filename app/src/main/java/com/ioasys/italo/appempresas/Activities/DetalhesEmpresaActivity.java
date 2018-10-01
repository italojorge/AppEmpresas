package com.ioasys.italo.appempresas.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        mDescricao = findViewById(R.id.detalhesEmpresaActivity_descricao_textView);
        mImagem = findViewById(R.id.detalhesempresa_imagem_imageView);
        toolbar = findViewById(R.id.detalhesempresa_toolbar);

        mDescricao.setText(bundle.getString("description"));

        configuracoesToolBar(bundle);

        if (bundle.getString("image") == null) return;

        GlideApp.with(getApplicationContext())
                .load(ApiUtils.BASE_IMAGE + bundle.getString("image"))
                .into(mImagem);

    }

    //altera titulo da Toolbar de acordo com nome da empresa e add botao voltar
    private void configuracoesToolBar(Bundle bundle) {

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(bundle.getString("enterprise_name"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    //finaliza a activity ao pressionar botao voltar
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
