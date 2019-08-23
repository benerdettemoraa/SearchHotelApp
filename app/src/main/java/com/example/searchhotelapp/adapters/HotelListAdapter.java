package com.example.searchhotelapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchhotelapp.R;
import com.example.searchhotelapp.adapters.viewHolder.HotelViewHolder;
import com.example.searchhotelapp.callback.HotelClickListener;
import com.example.searchhotelapp.models.Hotel;

import java.util.List;

public class HotelListAdapter extends RecyclerView.Adapter<HotelViewHolder> {
    private List<Hotel> hotels;
    private HotelClickListener hotelClickListener;
    public HotelListAdapter(List<Hotel> hotels, HotelClickListener hotelClickListener){
        this.hotels = hotels;
        this.hotelClickListener = hotelClickListener;
    }



    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_hotel_item, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = hotels.get(position);

        if (hotel != null) {
            holder.bind(hotel, hotelClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public void updateList(List<Hotel> hotels){
        this.hotels = hotels;
        notifyDataSetChanged();
    }
}
