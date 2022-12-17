package com.thingspeak.thingspeak_iot;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {
    public static String BASE_URL="https://api.thingspeak.com";
    public static Retrofit retrofit;

    public static Retrofit getInstance(){
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;

    }
}
