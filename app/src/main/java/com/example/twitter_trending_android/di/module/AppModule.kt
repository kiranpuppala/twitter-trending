package com.example.twitter_trending_android.di.module

import android.app.Application
import androidx.room.Room
import com.example.twitter_trending_android.data.repository.TwitterRepositoryImpl
import com.example.twitter_trending_android.data.db.TwitterDatabase
import com.example.twitter_trending_android.data.db.dao.TrendDao
import com.example.twitter_trending_android.data.network.service.TwitterApiService
import com.example.twitter_trending_android.data.repository.TwitterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTrendDatabase(application: Application): TwitterDatabase {
        return Room.databaseBuilder(application, TwitterDatabase::class.java, "twitter_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTrendDao(db:TwitterDatabase):TrendDao {
        return db.trendDao()
    }

    @Provides
    @Singleton
    fun provideRepoService(): TwitterApiService {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TwitterApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTwitterRepository(
        twitterApiService: TwitterApiService,
        trendsDao: TrendDao
    ): TwitterRepository {
        return TwitterRepositoryImpl(twitterApiService,trendsDao)
    }
}
