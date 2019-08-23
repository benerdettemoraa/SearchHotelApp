package com.example.searchhotelapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.searchhotelapp.callback.HotelFetchListener;
import com.example.searchhotelapp.models.Hotel;
import com.example.searchhotelapp.services.HotelService;
import com.google.gson.Gson;

import java.util.Locale;

public class HotelInfoActivity extends AppCompatActivity {
    private ProgressBar loadingIndicator;
    private String hotelId, accessToken;

    private TextView txtName, txtPhone, txtFax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_info);

        txtFax = findViewById(R.id.txt_hotel_fax);
        txtName = findViewById(R.id.txt_hotel_name);
        txtPhone = findViewById(R.id.txt_hotel_phone);

        loadingIndicator = findViewById(R.id.progress_circular);

        Intent intent = getIntent();
        hotelId = intent.getStringExtra(MainActivity.HOTEL_ID_KEY);
        accessToken = intent.getStringExtra(MainActivity.ACCESS_TOKEN_KEY);

        HotelService.retrieveHotel(accessToken, hotelId, new HotelFetchListener() {
            @Override
            public void onFetchSuccess(Object hotel) {
                if (loadingIndicator != null && loadingIndicator.isShown()) {
                    loadingIndicator.setVisibility(View.GONE);
                }

                txtName.setText(((Hotel)hotel).getName());
                txtPhone.setText(String.format(Locale.getDefault(), "Tel: %s", ((Hotel)hotel).getContact().getPhone()));
                txtFax.setText(String.format(Locale.getDefault(), "Fax: %s", ((Hotel)hotel).getContact().getFax()));
                Log.d("HOTEL_OBJECT", new Gson().toJson(hotel));
            }

            @Override
            public void onError(String error) {
                if (loadingIndicator != null && loadingIndicator.isShown()) {
                    loadingIndicator.setVisibility(View.GONE);
                }
                Toast.makeText(HotelInfoActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
