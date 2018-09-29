package com.ioasys.italo.appempresas.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.Toast;

import com.ioasys.italo.appempresas.R;
import com.ioasys.italo.appempresas.RecycleViewEmpresas.Empresa;
import com.ioasys.italo.appempresas.RecycleViewEmpresas.EmpresaAdapter;
import com.ioasys.italo.appempresas.RetrofitResources.model.Get.Enterprise;
import com.ioasys.italo.appempresas.RetrofitResources.model.Get.EnterpriseIndex;
import com.ioasys.italo.appempresas.RetrofitResources.remote.APIService;
import com.ioasys.italo.appempresas.RetrofitResources.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesquisarActivity extends AppCompatActivity {

    private APIService mAPIService;
    private EmpresaAdapter mAdapter;
    private String mUid,mClient,mAccess_token, mSeach;

    private RecyclerView mRecyclerView;
    private SearchView mSearchView;

    @Override
    protected void onStart() {
        super.onStart();

        //deu bom até aqui

        //exibirEmpresasDoServidor("UMB_2kzgXo9D2BvmbOzQNw", "testeapple@ioasys.com.br", "yK4ZwbuNVlhtw4VfXhCn6Q", query);
        exibirEmpresasDoServidor(mClient,mUid,mAccess_token,"Bar");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        configuracoesToolBar();
        mAPIService = ApiUtils.getAPIService();
        Bundle tokens = getIntent().getExtras();
        mRecyclerView = findViewById(R.id.activityPesquisar_recycleView);

        if(tokens != null){
            mUid = tokens.getString("uid");
            mClient = tokens.getString("client");
            mAccess_token = tokens.getString("acess-token");
        }
    }

    private void configuracoesToolBar() {
        Toolbar toolbar = findViewById(R.id.wld_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pesquisar_menu, menu);

        mSearchView = (SearchView) menu.findItem(R.id.menu_seachView).getActionView();
        mSearchView.setQueryHint("Pesquisar");

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

    public void exibirEmpresasDoServidor(String client, String uid, String acess_token,String search) {

        mAPIService.exibirEmpresas(uid,acess_token,client,search).enqueue(new Callback<EnterpriseIndex>() {
            @Override
            public void onResponse(Call<EnterpriseIndex> call, Response<EnterpriseIndex> response) {
//usarFinish no terceiro layout
                if(response.isSuccessful()) {
                    exibeRecycleView(response);
                }else{
                    Toast.makeText(getApplicationContext(),"Conexão expirou, faça o login novamente!",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<EnterpriseIndex> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Falha na rede", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void exibeRecycleView(Response<EnterpriseIndex> response) {
        List<Enterprise> listEnterprises;
        ArrayList<Empresa> empresas = new ArrayList<Empresa>();

        listEnterprises = response.body().getEnterprises();
        if (listEnterprises == null){
            Toast.makeText(getApplicationContext(),"Pesquisa não encontrou resultados",Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
            for (Enterprise enterprise :listEnterprises){
                Empresa empresa = new Empresa(enterprise.getEnterpriseName(),enterprise.getEnterpriseType().getEnterpriseTypeName(),enterprise.getCountry());
                empresas.add(empresa);
            }
            setupRecycler(empresas);
        }
    }


}
