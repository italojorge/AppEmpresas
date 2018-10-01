package com.ioasys.italo.appempresas.RetrofitResources.model.Authentication;

    import com.google.gson.annotations.Expose;
    import com.google.gson.annotations.SerializedName;

public class SignIn {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;

}