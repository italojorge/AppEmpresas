package com.ioasys.italo.appempresas.RetrofitResources.remote;

import com.ioasys.italo.appempresas.RetrofitResources.model.Get.EnterpriseIndex;
import com.ioasys.italo.appempresas.RetrofitResources.model.Post.SignIn;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @POST("users/auth/sign_in")
    @FormUrlEncoded
    Call<SignIn> logar(@Field("email") String email,
                       @Field("password") String password);

    @GET("enterprises")
    Call<EnterpriseIndex> exibirEmpresas(@Header("uid") String uid, @Header("access-token") String access_token
                                        ,@Header("client") String cliente, @Query("name") String search);
                                //;
}
