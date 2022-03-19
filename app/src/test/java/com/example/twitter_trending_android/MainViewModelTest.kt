package com.example.twitter_trending_android

import com.example.twitter_trending_android.presentation.MainViewModel
import com.example.twitter_trending_android.presentation.TrendViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun test_getTrends() = runTest {
        val mainViewModel =
            MainViewModel(FakeTwitterRepositoryImpl(), mainCoroutineRule.dispatcherProvider)
        assertEquals(
            mainViewModel.trends.first(), listOf(
                TrendViewState(
                    name = "India",
                    url = "https://twitter.com/trend/india",
                    promoted_content = "",
                    query = "",
                    tweet_volume = 1000L
                )
            )
        )
    }
}
