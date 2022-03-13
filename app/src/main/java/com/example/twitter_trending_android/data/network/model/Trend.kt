package com.example.twitter_trending_android.data.network.model

data class Trend(
    val name : String,
    val url: String,
    val promoted_content: String,
    val query: String,
    val tweet_volume: Long
)

data class Location(
    val name : String,
    val woeid : Int
)

data class TrendApiResponse(
    val trends : List<Trend>,
    val as_of : String,
    val created_at : String,
    val locations: List<Location>
)

