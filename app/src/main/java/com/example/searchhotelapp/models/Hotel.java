package com.example.searchhotelapp.models;

import com.google.gson.annotations.SerializedName;

public class Hotel {
    @SerializedName("name")
    private String name;
    @SerializedName("hotelDistance")
    private HotelDistance hotelDistance;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("contact")
    private Contact contact;
    @SerializedName("rating")
    private int rating;

    private class Contact {
        @SerializedName("phone")
        String phone;
        @SerializedName("fax")
        String fax;
    }

    private class HotelDistance {
        @SerializedName("distance")
        float distance;
        @SerializedName("distanceUnit")
        String distanceUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HotelDistance getHotelDistance() {
        return hotelDistance;
    }

    public void setHotelDistance(HotelDistance hotelDistance) {
        this.hotelDistance = hotelDistance;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
