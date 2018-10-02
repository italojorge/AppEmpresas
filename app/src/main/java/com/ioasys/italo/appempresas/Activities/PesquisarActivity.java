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
    private ArrayList<Empresa> empresas;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private TextView mPesquiseAcima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        encontrandoViewsPorId();
        configuracoesToolBar();

        mAPIService = ApiUtils.getAPIService();
        Bundle tokens = getIntent().getExtras(); //recupera tokens da LoginActivity

        if (tokens != null) {
            mUid = tokens.getString("uid");
            mClient = tokens.getString("client");
            mAccess_token = tokens.getString("access-token");
        }
    }

    public void encontrandoViewsPorId() {
        mRecyclerView = findViewById(R.id.pesquisar_recycleView);
        mPesquiseAcima = findViewById(R.id.pesquisar_pesquiseAcima_TextView);
        mToolbar = findViewById(R.id.pesquisar_toolbar);
    }

    public void configuracoesToolBar() {
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pesquisar_menu, menu);

        SearchView mSearchView = (SearchView) menu.findItem(R.id.menu_seachView).getActionView();
        mSearchView.setQueryHint("Pesquisar");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mRecyclerView.setVisibility(View.VISIBLE);
                mPesquiseAcima.setVisibility(View.INVISIBLE);

                verificaEmpresaNoServidor(mClient, mUid, mAccess_token, query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    public void ExibeRecycleView(ArrayList<Empresa> empresas) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);

        mAdapter = new EmpresaAdapter(empresas, this);
        mRecyclerView.setAdapter(mAdapter);

        // Adicionando um divisor entre linhas, para uma melhor visualizacao.
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    public void verificaEmpresaNoServidor(String client, String uid, String acess_token, String search) {

        mAPIService.exibirEmpresas(uid, acess_token, client, search).enqueue(new Callback<EnterprisesList>() {
            @Override
            public void onResponse(Call<EnterprisesList> call, Response<EnterprisesList> response) {
                if (response.isSuccessful()) {
                    RecebeEmpresasDoServidor(response);
                    ExibeRecycleView(empresas);
                    toastQuantidadeEmpresas(); //exibe um toast com a quantidade de empresas encontradas com a pesquisa
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

    //metodo responsavel por recuperar apenas as informacoes das empresas necessarias para exibicao
    public void RecebeEmpresasDoServidor(Response<EnterprisesList> response) {
        List<Enterprise> listEnterprises;
        empresas = new ArrayList<>();

        listEnterprises = response.body().getEnterprises();
        if (listEnterprises == null) {
            Toast.makeText(getApplicationContext(), "Pesquisa não encontrou resultados", Toast.LENGTH_SHORT).show();
            return;
        }
        for (Enterprise enterprise : listEnterprises) {
            Empresa empresa = new Empresa(enterprise.getEnterpriseName(),
                    enterprise.getEnterpriseType().getEnterpriseTypeName(),
                    enterprise.getCountry(),
                    enterprise.getDescription(),
                    enterprise.getPhoto());
            empresas.add(empresa);
        }
    }

    public void toastQuantidadeEmpresas() { //exibe quantidade de empresas encontradas de acordo com a pesquisa
        if (mAdapter.getItemCount() == 1) {
            Toast.makeText(getApplicationContext(), mAdapter.getItemCount() + " empresa encontrada", Toast.LENGTH_SHORT).show();
        } else if (mAdapter.getItemCount() == 0) {
            Toast.makeText(getApplicationContext(), "Nenhuma empresa encontrada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), mAdapter.getItemCount() + " empresas encontradas", Toast.LENGTH_SHORT).show();
        }
    }


}
