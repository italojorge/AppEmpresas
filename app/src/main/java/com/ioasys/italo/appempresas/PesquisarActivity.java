package com.ioasys.italo.appempresas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.ioasys.italo.appempresas.controle.EmpresaAdapter;
import com.ioasys.italo.appempresas.entidades.Empresa;

import java.util.ArrayList;

public class PesquisarActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    private EmpresaAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);
        Toolbar toolbar = findViewById(R.id.wld_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        mRecyclerView = findViewById(R.id.activityPesquisar_recycleView);

        Empresa empresa1= new Empresa("Empresa1","vendas","brasil");
        Empresa empresa2 = new Empresa("Empresa2","cerveza","brasil");
        Empresa empresa3 = new Empresa("Empresa3","dorgas","brasil");
        Empresa empresa4 = new Empresa("Empresa4","dorgas","brasil");
        Empresa empresa5 = new Empresa("Empresa5","dorgas","brasil");
        Empresa empresa6 = new Empresa("Empresa6","dorgas","brasil");
        Empresa empresa7 = new Empresa("Empresa7","dorgas","brasil");
        Empresa empresa8 = new Empresa("Empresa8","dorgas","brasil");
        Empresa empresa9 = new Empresa("Empresa9","dorgas","brasil");

        ArrayList<Empresa> empresas = new ArrayList<Empresa>();
        empresas.add(empresa1);
        empresas.add(empresa2);
        empresas.add(empresa3);
        empresas.add(empresa4);
        empresas.add(empresa5);
        empresas.add(empresa6);
        empresas.add(empresa7);
        empresas.add(empresa8);
        empresas.add(empresa9);
        setupRecycler(empresas);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pesquisar_menu, menu);
        return true;
    }

    private void setupRecycler(ArrayList<Empresa> empresas) {

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        mAdapter = new EmpresaAdapter(empresas);
        mRecyclerView.setAdapter(mAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }


}
