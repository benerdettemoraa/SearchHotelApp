package com.example.searchhotelapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HotelMedia implements Serializable {
    @SerializedName("uri")
    public String uri;
    @SerializedName("category")
    public String category;
}
