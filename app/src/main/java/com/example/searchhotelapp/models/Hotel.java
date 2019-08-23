package com.example.searchhotelapp.models;

import com.google.gson.annotations.SerializedName;

public class Hotel {
    @SerializedName("hotelId")
    private String hotelId;
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

    public class Contact {
        @SerializedName("phone")
        String phone;
        @SerializedName("fax")
        String fax;

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    public class HotelDistance {
        @SerializedName("distance")
        float distance;
        @SerializedName("distanceUnit")
        String distanceUnit;

        public void setDistance(float distance) {
            this.distance = distance;
        }

        public void setDistanceUnit(String distanceUnit) {
            this.distanceUnit = distanceUnit;
        }

        public float getDistance() {
            return distance;
        }

        public String getDistanceUnit() {
            return distanceUnit;
        }
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
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
