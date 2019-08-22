package com.example.searchhotelapp.api;

import com.example.searchhotelapp.models.Hotel;
import com.google.gson.annotations.SerializedName;

public class HotelInfo {
    @SerializedName("hotel")
    private Hotel hotel;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
