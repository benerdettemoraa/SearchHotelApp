package com.example.searchhotelapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.searchhotelapp.callback.HotelFetchListener;
import com.example.searchhotelapp.callback.ImageLoadingCallback;
import com.example.searchhotelapp.models.Hotel;
import com.example.searchhotelapp.models.HotelMedia;
import com.example.searchhotelapp.services.HotelService;
import com.example.searchhotelapp.util.BasicUtils;
import com.google.gson.Gson;

import java.util.Locale;

public class HotelInfoActivity extends AppCompatActivity implements ImageLoadingCallback {
    private ProgressBar loadingIndicator;
    //    private String hotelId, accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_info);

        TextView txtFax = findViewById(R.id.txt_hotel_fax);
        TextView txtName = findViewById(R.id.txt_hotel_name);
        TextView txtPhone = findViewById(R.id.txt_hotel_phone);
        TextView txtDesc = findViewById(R.id.txt_hotel_description);
        ImageView imgHotel = findViewById(R.id.img_hotel_image);

        loadingIndicator = findViewById(R.id.progress_circular_one);

        Intent intent = getIntent();
        Hotel hotel = (Hotel) intent.getSerializableExtra(MainActivity.HOTEL_KEY);

        if (hotel == null){
            return;
        }
        if (hotel.media != null && !hotel.media.isEmpty()){
            HotelMedia media = (hotel).media.get(0);
            BasicUtils.loadImageToView(imgHotel, media.uri, false, this);
        }
        txtDesc.setText((hotel).description != null?(hotel).description.text: "");
        txtName.setText((hotel).getName());
        txtPhone.setText(String.format(Locale.getDefault(), "Tel: %s", (hotel).getContact().getPhone()));
        txtFax.setText(String.format(Locale.getDefault(), "Fax: %s", (hotel).getContact().getFax()));
        Log.d("HOTEL_OBJECT", new Gson().toJson(hotel));
    }

    @Override
    public void loadingFinished() {
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void loadingFailed() {
        loadingIndicator.setVisibility(View.GONE);
    }
}
