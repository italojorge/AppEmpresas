package com.ioasys.italo.appempresas.RetrofitResources.remote;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://empresas.ioasys.com.br/api/v1/";

    public static final String BASE_IMAGE = "http://empresas.ioasys.com.br/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
