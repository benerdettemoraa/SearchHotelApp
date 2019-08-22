package com.example.searchhotelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.searchhotelapp.api.APIClient;
import com.example.searchhotelapp.api.APIService;
import com.example.searchhotelapp.models.Hotel;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APIService service = APIClient.getInstance().create(APIService.class);

        Call<List<Hotel>> call = service.findHotels("PAR");

        call.enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                Log.d("ListOfHotels", new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                Log.d("ListOfHotelsError", t.getMessage() == null? "Error occurred": t.getMessage());
            }
        });
    }
}
