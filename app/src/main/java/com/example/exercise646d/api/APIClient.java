package com.example.exercise646d.api;

import android.content.Context;

import com.example.exercise646d.utils.ReusableValues;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit getRetrofit() {
        Gson gson =
                new GsonBuilder()
                        .serializeNulls()
                        .serializeSpecialFloatingPointValues()
                        .setLenient()
                        .create();

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(ReusableValues.BASE_URL)
                .build();
    }

    public static APIInterface getAPIService() {
        return getRetrofit().create(APIInterface.class);
    }
}
