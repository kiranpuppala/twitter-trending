package com.example.twitter_trending_android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.twitter_trending_android.data.db.dao.TrendDao
import com.example.twitter_trending_android.data.db.entity.TableTrend

@Database(entities = [TableTrend::class],version = 1)
abstract class TwitterDatabase:RoomDatabase() {
    abstract fun trendDao() : TrendDao
}
