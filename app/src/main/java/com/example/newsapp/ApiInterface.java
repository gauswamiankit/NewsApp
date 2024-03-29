package com.example.newsapp;

import com.example.newsapp.Model.Everything;
import com.example.newsapp.Model.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<Headlines> getHeadlines(

            @Query("country") String country,
            @Query("apiKey") String apiKey

    );
    @GET ("top-headlines")
    Call<Headlines> getCategoryNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apikey

    );
    @GET("everything")
    Call<Headlines> getSpecificData(

            @Query("q") String query,
            @Query("apiKey") String apiKey

    );


//        @GET("v2/top-headlines")
//        Call<ApiResponse> getTopArticlesByCategory(
//                @Query("category") String category,
//                @Query("country") String country,
//                @Query("pageSize") String pageSize,
//                @Query("apiKey") String apiKey);
//
//        @GET("v2/everything")
//        Call<ApiResponse> searchQuery(
//                @Query("q") String query,
//                @Query("apiKey") String apiKey);
//    }

}
