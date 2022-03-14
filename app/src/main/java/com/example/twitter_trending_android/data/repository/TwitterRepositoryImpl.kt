package com.example.twitter_trending_android.data.repository

import android.util.Log
import com.example.twitter_trending_android.data.Resource
import com.example.twitter_trending_android.data.db.dao.TrendDao
import com.example.twitter_trending_android.data.db.entity.TableTrend
import com.example.twitter_trending_android.data.db.mapper.DBMapper
import com.example.twitter_trending_android.data.network.networkBoundResource
import com.example.twitter_trending_android.data.network.service.TwitterApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TwitterRepositoryImpl(
    private val twitterApiService: TwitterApiService,
    private val trendsDao: TrendDao
) : TwitterRepository {

    private val TAG = "TwitterRepositoryImpl"

    override suspend fun loadTrendsFromApi() {
        withContext(Dispatchers.IO) {
            try {
                val response = twitterApiService.trending()
                if (response.isSuccessful) {
                    val trends = response.body()?.get(0)?.trends
                    trends?.let {
                        trendsDao.updateTrends(DBMapper.mapToTrendTable(it))
                    }
                } else {
                    Log.e(TAG, "fetchTrends: ${response.message()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getTrends(): Flow<Resource<List<TableTrend>>> {
        return networkBoundResource(
            query = {
                trendsDao.getTrends()
            },
            fetch = {
                twitterApiService.trending()
            },
            saveFetchResult = { response ->
                if (response.isSuccessful) {
                    val trends = response.body()?.get(0)?.trends
                    trends?.let {
                        trendsDao.updateTrends(DBMapper.mapToTrendTable(it))
                    }
                } else {
                    Log.e(TAG, "fetchTrends: ${response.message()}")
                }
            }
        )
    }
}
