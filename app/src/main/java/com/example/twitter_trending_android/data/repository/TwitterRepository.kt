package com.example.twitter_trending_android.data.repository

import com.example.twitter_trending_android.data.Resource
import com.example.twitter_trending_android.data.db.entity.TableTrend
import kotlinx.coroutines.flow.Flow

interface TwitterRepository {
    suspend fun loadTrendsFromApi()

    fun getTrends(): Flow<Resource<List<TableTrend>>>
}
