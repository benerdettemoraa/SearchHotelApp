package com.example.searchhotelapp.services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.searchhotelapp.api.APIClient;
import com.example.searchhotelapp.api.APIService;
import com.example.searchhotelapp.api.Token;
import com.example.searchhotelapp.callback.AuthTokenCallback;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenServices {
    private static APIService apiService = APIClient.getInstance().create(APIService.class);
    public static void getAuthToken(String clientId, String clientSecret, String grantType, final AuthTokenCallback authCallback) {
        final Call<Token> token = apiService.authenticate(clientId, clientSecret, grantType);
        token.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(@NonNull Call<Token> call, @NonNull Response<Token> response) {
                ResponseBody responseBody = response.errorBody();
                Token object = response.body();
                try {
                    if (object != null) {
                        authCallback.authSuccess(object);
                    } else {
                        if (response.code() != 200) {
                            if (responseBody != null) {
                                authCallback.authFailed(responseBody.string());
                            }
                        }
                    }
                } catch (Exception e) {
                    authCallback.authFailed("Unknown Error. Please try again.");
                    Log.d("Auth_token_error_1", "Unknown Error. Please try again. => " + e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Token> call, @NonNull Throwable t) {
                authCallback.authFailed(t.getMessage());
                Log.d("Auth_token_error_2", t.getMessage());
            }
        });
    }
}
