package com.example.twitter_trending_android.domain.repository

import com.example.twitter_trending_android.data.Resource
import com.example.twitter_trending_android.data.db.entity.TableTrend
import com.example.twitter_trending_android.data.network.model.Trend
import com.example.twitter_trending_android.data.network.model.TrendApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface TwitterRepository {
    suspend fun loadTrendsFromApi()

    fun getTrends(): Flow<Resource<List<TableTrend>>>
}
