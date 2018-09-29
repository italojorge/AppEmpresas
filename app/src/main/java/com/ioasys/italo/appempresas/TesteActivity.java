package com.ioasys.italo.appempresas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ioasys.italo.appempresas.data.model.Get.EnterpriseIndex;
import com.ioasys.italo.appempresas.data.model.Get.Enterprise;
import com.ioasys.italo.appempresas.data.model.Post.SignIn;
import com.ioasys.italo.appempresas.data.remote.APIService;
import com.ioasys.italo.appempresas.data.remote.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TesteActivity extends AppCompatActivity {

    private APIService mAPIService;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        resultado = findViewById(R.id.testeId);

        mAPIService = ApiUtils.getAPIService();
        logar("testeapple@ioasys.com.br","12341234");


    }

    public void logar(String email, String senha) {
        mAPIService.logar(email, senha).enqueue(new Callback<SignIn>() {
            @Override
            public void onResponse(Call<SignIn> call, Response<SignIn> response) {

                if(response.isSuccessful()) {
                    String uid,client,acess_token;
                    //String exibir = "uid: " + response.headers().get("uid").toString()+
                    //"acess-token"+response.headers().get("acess-token").toString() +
                    //"client"+response.headers().get("client").toString();
                   // resultado.setText("acess_token: "+response.headers().get("access-token").toString());
                    uid = response.headers().get("uid").toString();
                    client = response.headers().get("client").toString();
                    acess_token = response.headers().get("access-token").toString();

                    exibirEmpresas(client,uid,acess_token,"Bar");
                } else {
                    resultado.setText("Resposta deu ruim");
                }

                //Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
                //    if(response.isSuccessful()) {
                //         Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
                //      }
            }

            @Override
            public void onFailure(Call<SignIn> call, Throwable t) {
                resultado.setText("FalhaTotal");
            }
        });
    }

    public void exibirEmpresas(String client, String uid, String acess_token,String search) {
        mAPIService.exibirEmpresas(uid,acess_token,client,search).enqueue(new Callback<EnterpriseIndex>() {
            @Override
            public void onResponse(Call<EnterpriseIndex> call, Response<EnterpriseIndex> response) {

                if(response.isSuccessful()) {
                    List<Enterprise> lista = response.body().getEnterprises();
                    for (Enterprise teste:lista){
                        resultado.setText(
                              "nome: " + teste.getEnterpriseName() + "\n" +
                              "país: " + teste.getCountry()

                        );
                    }

                } else {
                    resultado.setText("Resposta deu ruim");
                }

                //Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
                //    if(response.isSuccessful()) {
                //         Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
                //      }
            }

            @Override
            public void onFailure(Call<EnterpriseIndex> call, Throwable t) {
                resultado.setText("FalhaTotal");
            }
        });
    }

}
