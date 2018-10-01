package com.ioasys.italo.appempresas.RetrofitResources.model.Enterprise;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnterpriseType {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("enterprise_type_name")
@Expose
private String enterpriseTypeName;

    public String getEnterpriseTypeName() {
    return enterpriseTypeName;
}

}
