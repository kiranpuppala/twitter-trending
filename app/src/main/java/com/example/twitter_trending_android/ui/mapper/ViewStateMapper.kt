package com.example.twitter_trending_android.ui.mapper

import com.example.twitter_trending_android.data.db.entity.TableTrend
import com.example.twitter_trending_android.ui.TrendViewState

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
