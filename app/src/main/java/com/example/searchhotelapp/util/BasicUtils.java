package com.example.searchhotelapp.util;

import android.view.Gravity;
import android.widget.ImageView;

import com.example.searchhotelapp.R;
import com.example.searchhotelapp.api.HotelInfo;
import com.example.searchhotelapp.callback.ImageLoadingCallback;
import com.example.searchhotelapp.models.Hotel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

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

    public static void loadImageToView(ImageView view, String imageUri, boolean circleTransform, final ImageLoadingCallback loadingCallback){
        RequestCreator creator = Picasso.get()
                .load(imageUri)
                .centerCrop(Gravity.CENTER);
        if (circleTransform){
            creator.transform(new CircleTransform());
        }
        creator.fit().into(view, new Callback() {
            @Override
            public void onSuccess() {
                loadingCallback.loadingFinished();
            }

            @Override
            public void onError(Exception e) {
                loadingCallback.loadingFailed();
            }
        });
    }
}
