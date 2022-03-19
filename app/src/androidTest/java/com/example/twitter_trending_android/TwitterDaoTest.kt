package com.example.twitter_trending_android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.twitter_trending_android.data.db.TwitterDatabase
import com.example.twitter_trending_android.data.db.entity.TableTrend
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executors

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 * Database tests are done as instrumented tests, meaning they will be in the androidTest source set.
 * This is because if you run these tests locally, they will use whatever version of SQLite
 * you have on your local machine, which could be very different from the version of SQLite
 * that ships with your Android device! Different Android devices also ship with different
 * SQLite versions, so it's helpful as well to be able to run these tests as instrumented
 * tests on different devices.
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class TwitterDaoTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: TwitterDatabase

    @Before
    fun setUpDB() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), TwitterDatabase::class.java
        ).setTransactionExecutor(Executors.newSingleThreadExecutor()).build()
    }

    @After
    fun closeDB() = database.close()

    @ExperimentalCoroutinesApi
    @Test
    fun testInsertAndGetTasks() = runTest(UnconfinedTestDispatcher()) {
        val input = listOf(
            TableTrend(
                id = 1,
                name = "India",
                url = "https://twitter.com/trend/india",
                promoted_content = "",
                query = "",
                tweet_volume = 1000L
            ),
            TableTrend(
                id = 2,
                name = "Russia",
                url = "https://twitter.com/trend/russia",
                promoted_content = "",
                query = "",
                tweet_volume = 3000L
            ),
        )

        database.trendDao().insertAllTrends(input)
        val output = database.trendDao().getTrends().first()
        assertThat(output).isEqualTo(input)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testDeleteTasks() = runTest(UnconfinedTestDispatcher()) {
        val input = listOf(
            TableTrend(
                id = 1,
                name = "India",
                url = "https://twitter.com/trend/india",
                promoted_content = "",
                query = "",
                tweet_volume = 1000L
            ),
            TableTrend(
                id = 2,
                name = "Russia",
                url = "https://twitter.com/trend/russia",
                promoted_content = "",
                query = "",
                tweet_volume = 3000L
            ),
        )

        database.trendDao().insertAllTrends(input)
        database.trendDao().clearTrends()
        val output = database.trendDao().getTrends().first()
        assertThat(output).isEqualTo(emptyList<TableTrend>())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testUpdateTasks() = runTest(UnconfinedTestDispatcher()) {
        val initialInput = listOf(
            TableTrend(
                id = 1,
                name = "India",
                url = "https://twitter.com/trend/india",
                promoted_content = "",
                query = "",
                tweet_volume = 1000L
            ),
            TableTrend(
                id = 2,
                name = "Russia",
                url = "https://twitter.com/trend/russia",
                promoted_content = "",
                query = "",
                tweet_volume = 3000L
            ),
        )

        database.trendDao().insertAllTrends(initialInput)

        val updatedInput = listOf(
            TableTrend(
                id = 1,
                name = "Japan",
                url = "https://twitter.com/trend/japan",
                promoted_content = "",
                query = "",
                tweet_volume = 1000L
            ),
            TableTrend(
                id = 2,
                name = "Africa",
                url = "https://twitter.com/trend/africa",
                promoted_content = "",
                query = "",
                tweet_volume = 3000L
            ),
        )

        database.trendDao().updateTrends(updatedInput)

        val output = database.trendDao().getTrends().first().toMutableList()

        assertThat(output).isNotEqualTo(initialInput)
        assertThat(output).isEqualTo(updatedInput)
    }
}
