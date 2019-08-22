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
    private static final String BASE_URL = "https://test.api.amadeus.com/";

    public static Retrofit getInstance(){
//        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
//        Interceptor headerAuthorizationInterceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                okhttp3.Request request = chain.request();
//                Headers headers = request.headers().newBuilder().add("Authorization", "Bearer "+Secret.ACCESS_TOKEN).build();
//                request = request.newBuilder().headers(headers).build();
//                return chain.proceed(request);
//            }
//        };
//        OkHttpClient client = clientBuilder.addInterceptor(headerAuthorizationInterceptor).build();
        if (retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(client)
                    .build();

        }
        return retrofit;
    }
}
