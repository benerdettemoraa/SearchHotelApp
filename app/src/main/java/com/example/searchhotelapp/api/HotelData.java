package com.example.searchhotelapp.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelData {
    @SerializedName("data")
    public List<HotelInfo> hotelInfoList;
}
