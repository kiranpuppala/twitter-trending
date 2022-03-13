package com.example.twitter_trending_android.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "trends_table")
@Parcelize
data class TableTrend(
    @PrimaryKey(autoGenerate = true) val id: Long=0,
    val name: String,
    val url: String,
    val promoted_content: String?,
    val query: String,
    val tweet_volume: Long
) : Parcelable
