package com.example.twitter_trending_android

import com.example.twitter_trending_android.data.Resource
import com.example.twitter_trending_android.data.db.entity.TableTrend
import com.example.twitter_trending_android.data.repository.TwitterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTwitterRepositoryImpl : TwitterRepository {

    override suspend fun loadTrendsFromApi() {}

    override fun getTrends(): Flow<Resource<List<TableTrend>>> {
        return flow {
            emit(
                Resource.Success(
                    listOf(
                        TableTrend(
                            id = 0,
                            name = "India",
                            url = "https://twitter.com/trend/india",
                            promoted_content = "",
                            query = "",
                            tweet_volume = 1000L
                        )
                    )
                )
            )
        }
    }
}
