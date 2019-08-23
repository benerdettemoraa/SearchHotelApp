package com.example.searchhotelapp.api;

import com.google.gson.annotations.SerializedName;

public class HotelSingle {
    @SerializedName("data")
    private HotelInfo hotelInfo;

    public HotelInfo getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(HotelInfo hotelInfo) {
        this.hotelInfo = hotelInfo;
    }
}
