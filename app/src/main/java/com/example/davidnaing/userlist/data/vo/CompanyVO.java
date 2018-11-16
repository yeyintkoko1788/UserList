package com.example.davidnaing.userlist.data.vo;

import com.google.gson.annotations.SerializedName;

public class CompanyVO {
    @SerializedName("name")
    private String name;

    @SerializedName("catchPhrase")
    private String catchPhrase;

    @SerializedName("bs")
    private String bs;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public String getBs() {
        return bs;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }
}
