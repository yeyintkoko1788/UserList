package com.example.davidnaing.userlist.data.vo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserVO {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("username")
    private String userName;

    @SerializedName("email")
    private String email;

    @SerializedName("address")
    private AddressVO address;

    @SerializedName("phone")
    private String phone;

    @SerializedName("website")
    private String website;

    @SerializedName("company")
    private CompanyVO company;



    public void setAddress(AddressVO address) {
        this.address = address;
    }

    public AddressVO getAddress() {
        return address;
    }

    public void setCompany(CompanyVO company) {
        this.company = company;
    }

    public CompanyVO getCompany() {
        return company;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }
}
