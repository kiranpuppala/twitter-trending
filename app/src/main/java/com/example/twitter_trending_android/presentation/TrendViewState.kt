package com.example.twitter_trending_android.presentation

data class TrendViewState(
    val name: String,
    val url: String,
    val promoted_content: String?,
    val query: String,
    val tweet_volume: Long
)
