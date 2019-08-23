package com.example.searchhotelapp.api;

import com.example.searchhotelapp.models.Hotel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    @GET("v2/shopping/hotel-offers")
    Call<HotelData> findHotels(@HeaderMap Map<String, String> headers, @Query("cityCode") String cityCode);

    @GET("v2/shopping/hotel-offers/by-hotel")
    Call<HotelSingle> findHotel(@HeaderMap Map<String, String> headers, @Query("hotelId") String hotelId);

    @FormUrlEncoded
    @POST("v1/security/oauth2/token")
    Call<Token> authenticate(@Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("grant_type") String grantType);
}
