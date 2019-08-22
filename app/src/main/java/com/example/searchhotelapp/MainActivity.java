package com.example.searchhotelapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchhotelapp.adapters.HotelListAdapter;
import com.example.searchhotelapp.api.APIClient;
import com.example.searchhotelapp.api.APIService;
import com.example.searchhotelapp.api.HotelData;
import com.example.searchhotelapp.api.HotelInfo;
import com.example.searchhotelapp.api.Secret;
import com.example.searchhotelapp.api.Token;
import com.example.searchhotelapp.callback.AuthTokenCallback;
import com.example.searchhotelapp.models.Hotel;
import com.example.searchhotelapp.services.TokenServices;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AuthTokenCallback {
    private HotelListAdapter hotelListAdapter;
    private ProgressBar loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingIndicator = findViewById(R.id.progress_circular);

        RecyclerView lstHotelsView = findViewById(R.id.lst_hotels);

        lstHotelsView.setLayoutManager(new LinearLayoutManager(this));
        hotelListAdapter = new HotelListAdapter(new ArrayList<Hotel>());
        lstHotelsView.setAdapter(hotelListAdapter);

        TokenServices.getAuthToken(Secret.CLIENT_ID, Secret.CLIENT_SECRET, Secret.GRANT_TYPE, this);
    }

    @Override
    public void authSuccess(Token token) {
        final APIService service = APIClient.getInstance().create(APIService.class);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token.getAccessToken());
        final Call<HotelData> hotelDataCall = service.findHotels(headers, "PAR");
        hotelDataCall.enqueue(new Callback<HotelData>() {
            @Override
            public void onResponse(Call<HotelData> call, Response<HotelData> response) {
                if (loadingIndicator != null && loadingIndicator.isShown()) {
                    loadingIndicator.setVisibility(View.GONE);
                }
                HotelData hotelData = response.body();
                if (hotelData != null) {
                    Log.d("ListOfHotels", new Gson().toJson(hotelData.getHotelInfoList()));
                    hotelListAdapter.updateList(mapHotelInfoToHotel(hotelData.getHotelInfoList()));
                }
            }

            @Override
            public void onFailure(Call<HotelData> call, Throwable t) {
                if (loadingIndicator != null && loadingIndicator.isShown()) {
                    loadingIndicator.setVisibility(View.GONE);
                }
                Log.d("ListOfHotelsError", t.getMessage() == null ? "Error occurred" : t.getMessage());
            }
        });
    }

    @Override
    public void authFailed(String error) {
        if (loadingIndicator != null && loadingIndicator.isShown()) {
            loadingIndicator.setVisibility(View.GONE);
        }
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private List<Hotel> mapHotelInfoToHotel(List<HotelInfo> hotelInfoList) {
        List<Hotel> hotels = new ArrayList<>();
        for (int i = 0; i < hotelInfoList.size(); i++) {
            Hotel hotel = hotelInfoList.get(i).getHotel();
            hotels.add(hotel);
        }

        Log.d("HotelsList", new Gson().toJson(hotels));
        return hotels;
    }
}
