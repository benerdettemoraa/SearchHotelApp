package com.example.searchhotelapp.api;

import com.example.searchhotelapp.models.Hotel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("/shopping/hotel-offers") //?cityCode=PAR
    Call<List<Hotel>> findHotels(@Query("cityCode") String cityCode);
}
