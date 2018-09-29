package com.ioasys.italo.appempresas.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ioasys.italo.appempresas.R;
import com.ioasys.italo.appempresas.RetrofitResources.model.Post.SignIn;
import com.ioasys.italo.appempresas.RetrofitResources.remote.APIService;
import com.ioasys.italo.appempresas.RetrofitResources.remote.ApiUtils;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button entrar;
    private EditText email;
    private EditText senha;
    private TextView usuarioOuSenhaInvalidos;
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        encontrandoViewsPorId();

        mAPIService = ApiUtils.getAPIService();

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarioOuSenhaInvalidos.setVisibility(View.INVISIBLE);
                String emailDigitado = email.getText().toString();
                String senhaDigitada = senha.getText().toString();

                validaSenha(senhaDigitada);

                if (validaEmail(emailDigitado)){
                    logar(emailDigitado,senhaDigitada);
                }
            }
        });

    }

    private boolean validaEmail(String emailDigitado) {
        if(emailDigitado.isEmpty()){
            email.setError("Campo de email está vazio");
        } else {

            if(isEmail(emailDigitado)){
                return true;
            } else {
                email.setError("Digite um endereço de email válido");
            }
        }
        return false;
    }

    private void validaSenha(String senhaDigitada) {
        if(senhaDigitada.isEmpty()){
            senha.setError("Campo de senha está vazio");
        }
    }

    private void encontrandoViewsPorId() {
        entrar = findViewById(R.id.loginActivity_entrar_button);
        email = findViewById(R.id.loginActivity_email_editText);
        senha = findViewById(R.id.loginActivity_senha_editText);
        usuarioOuSenhaInvalidos = findViewById(R.id.loginActivity_emailsenhainvalidos_textView);
    }

    private boolean isEmail(String email) {
        String REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email).find();
    }

    public void logar(String email, String senha) {
        mAPIService.logar(email, senha).enqueue(new Callback<SignIn>() {
            @Override
            public void onResponse(Call<SignIn> call, Response<SignIn> response) {

                if(response.isSuccessful()) {
                    String uid,client,acess_token;
                    uid = response.headers().get("uid").toString();
                    client = response.headers().get("client").toString();
                    acess_token = response.headers().get("access-token").toString();

                    Intent intent = new Intent(getApplicationContext(),PesquisarActivity.class);
                    intent.putExtra("uid",response.headers().get("uid").toString());
                    intent.putExtra("client",response.headers().get("client").toString());
                    intent.putExtra("acess-token",response.headers().get("access-token").toString());
                    startActivity(intent);
                } else {
                    usuarioOuSenhaInvalidos.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<SignIn> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Falha na Rede",Toast.LENGTH_LONG).show();
            }
        });
    }



}
