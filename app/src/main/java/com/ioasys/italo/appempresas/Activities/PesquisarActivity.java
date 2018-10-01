package com.ioasys.italo.appempresas.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.ioasys.italo.appempresas.R;
import com.ioasys.italo.appempresas.RecycleViewEmpresas.Empresa;
import com.ioasys.italo.appempresas.RecycleViewEmpresas.EmpresaAdapter;
import com.ioasys.italo.appempresas.RetrofitResources.model.Enterprise.Enterprise;
import com.ioasys.italo.appempresas.RetrofitResources.model.Enterprise.EnterprisesList;
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
    private String mUid;
    private String mClient;
    private String mAccess_token;

    private RecyclerView mRecyclerView;
    private SearchView mSearchView;
    private Toolbar mToolbar;
    private TextView mPesquiseAcima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        configuracoesToolBar();
        mAPIService = ApiUtils.getAPIService();
        Bundle tokens = getIntent().getExtras();

        mRecyclerView = findViewById(R.id.pesquisar_recycleView);
        mPesquiseAcima = findViewById(R.id.pesquisar_pesquiseAcima_TextView);

        if (tokens != null) {
            mUid = tokens.getString("uid");
            mClient = tokens.getString("client");
            mAccess_token = tokens.getString("acess-token");
        }
    }

    private void configuracoesToolBar() {
        mToolbar = findViewById(R.id.pesquisar_toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pesquisar_menu, menu);

        mSearchView = (SearchView) menu.findItem(R.id.menu_seachView).getActionView();
        mSearchView.setQueryHint("Pesquisar");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mRecyclerView.setVisibility(View.VISIBLE);
                mPesquiseAcima.setVisibility(View.INVISIBLE);

                //verificaConexaoComServidor("UMB_2kzgXo9D2BvmbOzQNw", "testeapple@ioasys.com.br", "yK4ZwbuNVlhtw4VfXhCn6Q", query);
                verificaConexaoComServidor(mClient, mUid, mAccess_token, query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    private void ExibeRecycleView(ArrayList<Empresa> empresas) {

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        mAdapter = new EmpresaAdapter(empresas, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    public void verificaConexaoComServidor(String client, String uid, String acess_token, String search) {

        mAPIService.exibirEmpresas(uid, acess_token, client, search).enqueue(new Callback<EnterprisesList>() {
            @Override
            public void onResponse(Call<EnterprisesList> call, Response<EnterprisesList> response) {
                if (response.isSuccessful()) {
                    RecebeEmpresasDoServidor(response);
                } else {
                    Toast.makeText(getApplicationContext(), "Conexão expirou, faça o login novamente!", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<EnterprisesList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na Rede", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void RecebeEmpresasDoServidor(Response<EnterprisesList> response) {
        List<Enterprise> listEnterprises;
        ArrayList<Empresa> empresas = new ArrayList<Empresa>();

        listEnterprises = response.body().getEnterprises();
        if (listEnterprises == null) {
            Toast.makeText(getApplicationContext(), "Pesquisa não encontrou resultados", Toast.LENGTH_SHORT).show();
            return;
        }
        //Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
        for (Enterprise enterprise : listEnterprises) {
            Empresa empresa = new Empresa(enterprise.getEnterpriseName(),
                    enterprise.getEnterpriseType().getEnterpriseTypeName(),
                    enterprise.getCountry(),
                    enterprise.getDescription(),
                    enterprise.getPhoto());
            empresas.add(empresa);
        }
        ExibeRecycleView(empresas);
        toastQuantidadeEmpresas();
    }

    public void toastQuantidadeEmpresas() {
        if (mAdapter.getItemCount() == 1) {
            Toast.makeText(getApplicationContext(), mAdapter.getItemCount() + " empresa encontrada", Toast.LENGTH_SHORT).show();
        } else if (mAdapter.getItemCount() == 0) {
            Toast.makeText(getApplicationContext(), "Nenhuma empresa encontrada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), mAdapter.getItemCount() + " empresas encontradas", Toast.LENGTH_SHORT).show();
        }
    }


}
