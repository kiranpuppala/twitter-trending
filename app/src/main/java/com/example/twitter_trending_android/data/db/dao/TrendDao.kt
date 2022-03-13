package com.example.twitter_trending_android.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.twitter_trending_android.data.db.entity.TableTrend
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendDao {
    /**
     * No need of suspend keyword because when Flow is created,
     * we're not actually doing any of the work yet.
     * That's why our function isn't a suspend one, it's just creating the object.
     * To actually get the items, we need to call collect on it,
     * and that needs to happen inside a coroutine,
     * because that's where the async stuff is actually happening.
     */
    @Query("SELECT * FROM trends_table")
    fun getTrends(): Flow<List<TableTrend>>

    @Query("DELETE FROM trends_table")
    suspend fun clearTrends()

    @Insert
    suspend fun insertAllTrends(trends: List<TableTrend>)

    @Transaction
    suspend fun updateTrends(trends:List<TableTrend>){
        clearTrends()
        insertAllTrends(trends)
    }
}
