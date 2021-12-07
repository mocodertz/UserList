package com.example.exercise646d.api;

import com.example.exercise646d.model.Group;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface APIInterface {
    @Headers({"Content-Type: application/json", "x-apikey: 5c5c7076f210985199db5488", "cache-control: no-cache"})
    @GET("group-1")
    Call<List<Group>> groupList();
}