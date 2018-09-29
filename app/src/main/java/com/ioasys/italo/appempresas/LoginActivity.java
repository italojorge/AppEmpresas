package com.ioasys.italo.appempresas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ioasys.italo.appempresas.data.model.Post.SignIn;
import com.ioasys.italo.appempresas.data.remote.APIService;
import com.ioasys.italo.appempresas.data.remote.ApiUtils;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button entrar;
    private EditText email;
    private EditText senha;
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        entrar = findViewById(R.id.loginActivity_entrar_button);
        email = findViewById(R.id.loginActivity_email_editText);
        senha = findViewById(R.id.loginActivity_senha_editText);

        mAPIService = ApiUtils.getAPIService();

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailDigitado = email.getText().toString();
                String senhaDigitada = senha.getText().toString();

                if(senhaDigitada.isEmpty()){
                    senha.setError("Campo de senha está vazio");
                }

                if(emailDigitado.isEmpty()){
                    email.setError("Campo de email está vazio");
                } else {

                    if(isEmail(emailDigitado)){
                        //funcionamento
              //          Toast.makeText(getApplicationContext(),"cai aqui",Toast.LENGTH_SHORT).show();
                        logar("oi","ola");



                        //teste abaixo


                    } else {
                        email.setError("Digite um endereço de email válido");
                    }
                }
            }
        });

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
            //    if(response.isSuccessful()) {
           //         Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
          //      }
            }

            @Override
            public void onFailure(Call<SignIn> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"deu ruim",Toast.LENGTH_SHORT).show();
            }
        });
    }



}
