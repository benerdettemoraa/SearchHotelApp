package com.example.searchhotelapp.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelData {
    @SerializedName("data")
    private List<HotelInfo> hotelInfoList;

    public List<HotelInfo> getHotelInfoList() {
        return hotelInfoList;
    }

    public void setHotelInfoList(List<HotelInfo> hotelInfoList) {
        this.hotelInfoList = hotelInfoList;
    }
}
