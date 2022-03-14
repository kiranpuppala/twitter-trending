package com.example.twitter_trending_android.presentation.mapper

import com.example.twitter_trending_android.data.db.entity.TableTrend
import com.example.twitter_trending_android.presentation.TrendViewState

object ViewStateMapper {
    fun mapTableDataToViewState(trends: List<TableTrend>) : List<TrendViewState>{
        return trends.map {
            with(it) {
                TrendViewState(
                    name,
                    url,
                    promoted_content,
                    query,
                    tweet_volume
                )
            }
        }
    }
}
