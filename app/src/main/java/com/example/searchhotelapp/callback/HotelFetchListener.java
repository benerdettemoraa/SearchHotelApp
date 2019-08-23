package com.example.searchhotelapp.callback;

public interface HotelFetchListener<T> {
    void onFetchSuccess(T hotels);

    void onError(String error);
}
