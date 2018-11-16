package com.example.davidnaing.userlist.data.vo;

import com.google.gson.annotations.SerializedName;

public class LatlngVO {
    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLng() {
        return lng;
    }
}
