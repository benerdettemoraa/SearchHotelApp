package com.example.searchhotelapp.api;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://test.api.amadeus.com/v2";

    public static Retrofit getInstance(){
        if (retrofit == null){
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            Interceptor headerAuthorizationInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    okhttp3.Request request = chain.request();
                    Headers headers = request.headers().newBuilder().add("Authorization", "Bearer "+Secret.ACCESS_TOKEN).build();
                    request = request.newBuilder().headers(headers).build();
                    return chain.proceed(request);
                }
            };
            clientBuilder.addInterceptor(headerAuthorizationInterceptor);
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
