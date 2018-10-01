package com.ioasys.italo.appempresas.RetrofitResources.model.Enterprise;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class EnterprisesList {

    @SerializedName("enterprises")
    @Expose
    private List<Enterprise> enterprises = null;

    public List<Enterprise> getEnterprises() {
        return enterprises;
    }

}

