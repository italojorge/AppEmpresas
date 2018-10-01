package com.ioasys.italo.appempresas.RetrofitResources.model.Enterprise;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Enterprise {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("enterprise_name")
    @Expose
    private String enterpriseName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("email_enterprise")
    @Expose
    private Object emailEnterprise;
    @SerializedName("facebook")
    @Expose
    private Object facebook;
    @SerializedName("twitter")
    @Expose
    private Object twitter;
    @SerializedName("linkedin")
    @Expose
    private Object linkedin;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("own_enterprise")
    @Expose
    private Boolean ownEnterprise;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("shares")
    @Expose
    private Integer shares;
    @SerializedName("share_price")
    @Expose
    private Integer sharePrice;
    @SerializedName("own_shares")
    @Expose
    private Integer ownShares;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("enterprise_type")
    @Expose
    private EnterpriseType enterpriseType;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }

    public String getCountry() {
        return country;
    }

    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }

}
