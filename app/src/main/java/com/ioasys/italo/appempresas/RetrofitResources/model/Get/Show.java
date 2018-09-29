package com.ioasys.italo.appempresas.RetrofitResources.model.Get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Show {

    @SerializedName("enterprise")
    @Expose
    private Enterprise enterprise;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
