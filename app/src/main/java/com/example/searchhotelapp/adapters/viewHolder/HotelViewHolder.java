package com.example.searchhotelapp.adapters.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchhotelapp.R;
import com.example.searchhotelapp.callback.HotelClickListener;
import com.example.searchhotelapp.callback.ImageLoadingCallback;
import com.example.searchhotelapp.models.Contact;
import com.example.searchhotelapp.models.Hotel;
import com.example.searchhotelapp.models.HotelDistance;
import com.example.searchhotelapp.models.HotelMedia;
import com.example.searchhotelapp.util.BasicUtils;

import java.util.Locale;

public class HotelViewHolder extends RecyclerView.ViewHolder implements ImageLoadingCallback{
    private TextView txtHotelName, txtDistance, txtRating, txtTelephone, txtFax;
    private ImageView imgAvatar;
    private ProgressBar progressBar;
    public HotelViewHolder(@NonNull View view) {
        super(view);
        txtHotelName = view.findViewById(R.id.txt_hotel_name);
        txtDistance = view.findViewById(R.id.txt_hotel_distance);
        txtRating = view.findViewById(R.id.txt_hotel_rating);
        txtTelephone = view.findViewById(R.id.txt_phone_contact);
        txtFax = view.findViewById(R.id.txt_fax_contact);
        imgAvatar = view.findViewById(R.id.img_hotel_avatar);
        progressBar = view.findViewById(R.id.progress_circular_one);
    }

    public void bind(final Hotel hotel, final HotelClickListener hotelClickListener){
        Contact contact = hotel.getContact();
        HotelDistance hotelDistance = hotel.getHotelDistance();
        txtRating.setText(String.format(Locale.getDefault(),"Rated: %d", hotel.getRating()));
        txtHotelName.setText(hotel.getName());
        if (hotel.media != null && !hotel.media.isEmpty()){
            HotelMedia media = hotel.media.get(0);
            BasicUtils.loadImageToView(imgAvatar, media.uri, true, this);
        }
        if (hotelDistance != null) {
            txtDistance.setText(String.format(Locale.getDefault(),
                    "Distance: %f%s", hotel.getHotelDistance().getDistance(),
                    hotel.getHotelDistance().getDistanceUnit()));
        }
        if (contact != null) {
            txtTelephone.setText(String.format(Locale.getDefault(), "Tel: %s", hotel.getContact().getPhone()));
            txtFax.setText(String.format(Locale.getDefault(), "Fax: %s", hotel.getContact().getFax()));
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hotelClickListener.onHotelClick(hotel);
            }
        });
    }

    @Override
    public void loadingFinished() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loadingFailed() {
        progressBar.setVisibility(View.GONE);
    }
}
