package com.example.searchhotelapp.adapters.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchhotelapp.R;
import com.example.searchhotelapp.models.Hotel;

import java.util.Locale;

public class HotelViewHolder extends RecyclerView.ViewHolder {
    private TextView txtHotelName, txtDistance, txtRating, txtTelephone, txtFax;
    public HotelViewHolder(@NonNull View view) {
        super(view);
        txtHotelName = view.findViewById(R.id.txt_hotel_name);
        txtDistance = view.findViewById(R.id.txt_hotel_distance);
        txtRating = view.findViewById(R.id.txt_hotel_rating);
        txtTelephone = view.findViewById(R.id.txt_phone_contact);
        txtFax = view.findViewById(R.id.txt_fax_contact);
    }

    public void bind(Hotel hotel){
        Hotel.Contact contact = hotel.getContact();
        Hotel.HotelDistance hotelDistance = hotel.getHotelDistance();
        txtRating.setText(String.format(Locale.getDefault(),"Rated: %d", hotel.getRating()));
        txtHotelName.setText(hotel.getName());
        if (hotelDistance != null) {
            txtDistance.setText(String.format(Locale.getDefault(),
                    "Distance: %f%s", hotel.getHotelDistance().getDistance(),
                    hotel.getHotelDistance().getDistanceUnit()));
        }
        if (contact != null) {
            txtTelephone.setText(hotel.getContact().getPhone());
            txtFax.setText(hotel.getContact().getFax());
        }
    }
}
