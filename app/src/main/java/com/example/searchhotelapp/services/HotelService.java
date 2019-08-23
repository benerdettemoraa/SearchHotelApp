package com.example.searchhotelapp.services;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.searchhotelapp.MainActivity;
import com.example.searchhotelapp.api.APIClient;
import com.example.searchhotelapp.api.APIService;
import com.example.searchhotelapp.api.HotelData;
import com.example.searchhotelapp.api.HotelSingle;
import com.example.searchhotelapp.api.Token;
import com.example.searchhotelapp.callback.HotelFetchListener;
import com.example.searchhotelapp.models.Hotel;
import com.example.searchhotelapp.util.BasicUtils;
import com.google.gson.Gson;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelService {
    private static APIService apiService = APIClient.getInstance().create(APIService.class);

    @SuppressWarnings("unchecked")
    public static void retrieveHotels(Token token, final HotelFetchListener fetchListener){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token.getAccessToken());
        final Call<HotelData> hotelDataCall = apiService.findHotels(headers, "PAR");

        hotelDataCall.enqueue(new Callback<HotelData>() {
            @Override
            public void onResponse(Call<HotelData> call, Response<HotelData> response) {
                HotelData hotelData = response.body();
                if (hotelData != null) {
                    fetchListener.onFetchSuccess(BasicUtils.mapHotelInfoToHotel(hotelData.getHotelInfoList()));
                    Log.d("ListOfHotels", new Gson().toJson(hotelData.getHotelInfoList()));
                }else {
                    fetchListener.onError("No Hotels Found");
                }
            }

            @Override
            public void onFailure(Call<HotelData> call, Throwable t) {
                fetchListener.onError(t.getMessage());
            }
        });
    }

    @SuppressWarnings("unchecked")
    public static void retrieveHotel(String token, final String hotelId, final HotelFetchListener fetchListener){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token);
        final Call<HotelSingle> hotelCall = apiService.findHotel(headers, hotelId);

        hotelCall.enqueue(new Callback<HotelSingle>() {
            @Override
            public void onResponse(Call<HotelSingle> call, Response<HotelSingle> response) {
                if (response.body() == null){
                    fetchListener.onError("No Information Found");
                    return;
                }
                Log.d("HOTEL_DATA", new Gson().toJson(response.body().getHotelInfo()));
                fetchListener.onFetchSuccess(response.body().getHotelInfo().getHotel());
            }

            @Override
            public void onFailure(Call<HotelSingle> call, Throwable t) {
                fetchListener.onError(t.getMessage());

            }
        });
    }
}