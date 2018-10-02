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
    private String emailEnterprise;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("linkedin")
    @Expose
    private String linkedin;
    @SerializedName("phone")
    @Expose
    private String phone;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmailEnterprise() {
        return emailEnterprise;
    }

    public void setEmailEnterprise(String emailEnterprise) {
        this.emailEnterprise = emailEnterprise;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getOwnEnterprise() {
        return ownEnterprise;
    }

    public void setOwnEnterprise(Boolean ownEnterprise) {
        this.ownEnterprise = ownEnterprise;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public Integer getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(Integer sharePrice) {
        this.sharePrice = sharePrice;
    }

    public Integer getOwnShares() {
        return ownShares;
    }

    public void setOwnShares(Integer ownShares) {
        this.ownShares = ownShares;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(EnterpriseType enterpriseType) {
        this.enterpriseType = enterpriseType;
    }
}
