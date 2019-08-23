package com.example.searchhotelapp.util;

import com.example.searchhotelapp.api.HotelInfo;
import com.example.searchhotelapp.models.Hotel;

import java.util.ArrayList;
import java.util.List;

public class BasicUtils {
    public static List<Hotel> mapHotelInfoToHotel(List<HotelInfo> hotelInfoList) {
        List<Hotel> hotels = new ArrayList<>();
        for (int i = 0; i < hotelInfoList.size(); i++) {
            Hotel hotel = hotelInfoList.get(i).getHotel();
            hotels.add(hotel);
        }
        return hotels;
    }
}
