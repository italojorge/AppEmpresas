package com.ioasys.italo.appempresas.data.remote;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://empresas.ioasys.com.br/api/v1/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
