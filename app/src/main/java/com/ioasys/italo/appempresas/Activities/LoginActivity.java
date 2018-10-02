package com.ioasys.italo.appempresas.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ioasys.italo.appempresas.R;
import com.ioasys.italo.appempresas.RetrofitResources.model.Authentication.SignIn;
import com.ioasys.italo.appempresas.RetrofitResources.remote.APIService;
import com.ioasys.italo.appempresas.RetrofitResources.remote.ApiUtils;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button mEntrar;
    private EditText mEmail;
    private EditText mSenha;
    private TextView mUsuarioOuSenhaInvalidos;
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        encontrandoViewsPorId();

        mAPIService = ApiUtils.getAPIService();

        mEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsuarioOuSenhaInvalidos.setVisibility(View.INVISIBLE);

                String emailDigitado = mEmail.getText().toString();
                String senhaDigitada = mSenha.getText().toString();

                if (emailValido(emailDigitado) & senhaValida(senhaDigitada)) {
                    logar(emailDigitado, senhaDigitada);
                }
            }
        });

    }

    public boolean emailValido(String emailDigitado) {
        if (emailDigitado.isEmpty()) {
            mEmail.setError("Campo de email está vazio");
        } else {

            if (isEmail(emailDigitado)) {
                return true;
            } else {
                mEmail.setError("Digite um endereço de email válido");
            }
        }
        return false;
    }

    public boolean senhaValida(String senhaDigitada) { //caso senha seja esteja vazia, exibe erro
        if (senhaDigitada.isEmpty()) {
            mSenha.setError("Campo de senha está vazio");
            return false;
        }
        return true;
    }

    public void encontrandoViewsPorId() { //linka os botoes da activity com os do xml
        mEntrar = findViewById(R.id.login_entrar_button);
        mEmail = findViewById(R.id.login_email_editText);
        mSenha = findViewById(R.id.login_senha_editText);
        mUsuarioOuSenhaInvalidos = findViewById(R.id.login_emailsenhainvalidos_textView);
    }

    public boolean isEmail(String email) { //verifica email atraves de expressao regular
        String REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email).find();
    }

    public void logar(String email, String senha) { //verifica login e senha do usuario no servidor
        mAPIService.logar(email, senha).enqueue(new Callback<SignIn>() {
            @Override
            public void onResponse(Call<SignIn> call, Response<SignIn> response) {

                if (response.isSuccessful()) {
                    String uid, client, access_token;
                    uid = response.headers().get("uid").toString();
                    client = response.headers().get("client").toString();
                    access_token = response.headers().get("access-token").toString();

                    Intent intent = new Intent(getApplicationContext(), PesquisarActivity.class);
                    enviaTokensParaActivity(uid, client, access_token, intent);
                    startActivity(intent);
                } else {
                    //texto exibindo: Usuario e/ou senha inválidos
                    mUsuarioOuSenhaInvalidos.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<SignIn> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na Rede", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void enviaTokensParaActivity(String uid, String client, String access_token, Intent intent) {
        intent.putExtra("uid", uid);
        intent.putExtra("client", client);
        intent.putExtra("access-token", access_token);
    }


}
