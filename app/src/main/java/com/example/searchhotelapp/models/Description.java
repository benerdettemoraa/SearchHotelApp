package com.example.searchhotelapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Description implements Serializable {
    @SerializedName("lang")
    public String language;
    @SerializedName("text")
    public String text;
}
