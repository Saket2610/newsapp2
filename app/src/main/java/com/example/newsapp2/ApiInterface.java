package com.example.newsapp2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    String BASE_URL="https://newsapi.org/v2/";


/*
    @GET("news")
    Call<mainNews> getNews(
            @Query("apikey") String key
          //  @Query("per_page") int per_page
            //@Query("query") String query
    );*/


    @GET("top-headlines")
    Call<mainNews> getNews(
            @Query("country") String country,
//            we can also directly pass any country instead of the string country
            @Query("pageSize") int pagesize,
            @Query("apiKey") String apikey
    );


    @GET("top-headlines")
    Call<mainNews> getcategoryNews(

            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pagesize,
            @Query("apiKey") String apikey
    );






}
