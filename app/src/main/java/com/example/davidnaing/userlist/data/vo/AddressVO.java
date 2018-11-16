package com.example.davidnaing.userlist.data.vo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AddressVO {
    @SerializedName("street")
    private String street;

    @SerializedName("suite")
    private String suite;

    @SerializedName("city")
    private String city;

    @SerializedName("zipcode")
    private String zipcode;

    @SerializedName("geo")
    private LatlngVO geo;

    public void setGeo(LatlngVO geo) {
        this.geo = geo;
    }

    public LatlngVO getGeo() {
        return geo;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getSuite() {
        return suite;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getZipcode() {
        return zipcode;
    }
}
