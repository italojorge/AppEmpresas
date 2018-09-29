package com.ioasys.italo.appempresas.data.model.Get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnterpriseType {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("enterprise_type_name")
@Expose
private String enterpriseTypeName;

public Integer getId() {
    return id;
}

public void setId(Integer id) {
    this.id = id;
}

public String getEnterpriseTypeName() {
    return enterpriseTypeName;
}

public void setEnterpriseTypeName(String enterpriseTypeName) {
    this.enterpriseTypeName = enterpriseTypeName;
}

}
