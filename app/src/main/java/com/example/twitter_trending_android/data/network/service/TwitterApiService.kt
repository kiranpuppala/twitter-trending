package com.example.twitter_trending_android.data.network.service

import com.example.twitter_trending_android.data.network.model.TrendApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface TwitterApiService {
    @GET("/trending")
    suspend fun trending(): Response<List<TrendApiResponse>>
}
