package com.ioasys.italo.appempresas.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ioasys.italo.appempresas.R;

public class DetalhesEmpresaActivity extends AppCompatActivity {

    private TextView mDescricao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhesempresa);

        Bundle bundle = getIntent().getExtras();
        mDescricao = findViewById(R.id.detalhesEmpresaActivity_descricao_textView);
        mDescricao.setText(bundle.getString("description"));

    }
}
