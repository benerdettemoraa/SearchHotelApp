package com.example.searchhotelapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchhotelapp.adapters.HotelListAdapter;
import com.example.searchhotelapp.api.Secret;
import com.example.searchhotelapp.api.Token;
import com.example.searchhotelapp.callback.AuthTokenCallback;
import com.example.searchhotelapp.callback.HotelClickListener;
import com.example.searchhotelapp.callback.HotelFetchListener;
import com.example.searchhotelapp.models.Hotel;
import com.example.searchhotelapp.services.HotelService;
import com.example.searchhotelapp.services.TokenServices;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AuthTokenCallback, HotelClickListener {
    public static final String HOTEL_ID_KEY = "com.example.searchhotelapp.MainActivity.HOTEL_ID";
    public static final String ACCESS_TOKEN_KEY = "com.example.searchhotelapp.MainActivity.ACCESS_TOKEN";
    private HotelListAdapter hotelListAdapter;
    private ProgressBar loadingIndicator;
    private Token token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingIndicator = findViewById(R.id.progress_circular);

        RecyclerView lstHotelsView = findViewById(R.id.lst_hotels);

        lstHotelsView.setLayoutManager(new LinearLayoutManager(this));
        hotelListAdapter = new HotelListAdapter(new ArrayList<Hotel>(), this);
        lstHotelsView.setAdapter(hotelListAdapter);

        TokenServices.getAuthToken(Secret.CLIENT_ID, Secret.CLIENT_SECRET, Secret.GRANT_TYPE, this);
    }

    @Override @SuppressWarnings("unchecked")
    public void authSuccess(Token token) {
        this.token = token;
        HotelService.retrieveHotels(token, new HotelFetchListener() {
            @Override
            public void onFetchSuccess(Object hotels) {
                if (loadingIndicator != null && loadingIndicator.isShown()) {
                    loadingIndicator.setVisibility(View.GONE);
                }
                Log.d("ListOfHotels", new Gson().toJson(hotels));
                hotelListAdapter.updateList((List<Hotel>)hotels);
            }

            @Override
            public void onError(String message) {
                if (loadingIndicator != null && loadingIndicator.isShown()) {
                    loadingIndicator.setVisibility(View.GONE);
                }
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onHotelClick(Hotel hotel) {
        Intent intent = new Intent(this, HotelInfoActivity.class);
        intent.putExtra(HOTEL_ID_KEY, hotel.getHotelId());
        intent.putExtra(ACCESS_TOKEN_KEY, token.getAccessToken());
        startActivity(intent);
    }
}
