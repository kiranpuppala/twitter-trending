package com.example.twitter_trending_android.data.db.mapper

import com.example.twitter_trending_android.data.db.entity.TableTrend
import com.example.twitter_trending_android.data.network.model.Trend

object DBMapper {
    fun mapToTrendTable(trends: List<Trend>) =
        trends.map {
            with(it) {
                TableTrend(
                    name = name,
                    url = url,
                    promoted_content = promoted_content,
                    query = query,
                    tweet_volume = tweet_volume
                )
            }
        }
}

