package com.example.searchhotelapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HotelDistance implements Serializable {
    @SerializedName("distance")
    private float distance;
    @SerializedName("distanceUnit")
    private String distanceUnit;

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
    }
}
