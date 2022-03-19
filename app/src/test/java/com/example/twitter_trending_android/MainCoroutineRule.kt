package com.example.twitter_trending_android

import com.example.twitter_trending_android.presentation.CoroutineDispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class MainCoroutineRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
    var dispatcherProvider: CoroutineDispatcherProvider = CoroutineDispatcherProvider()
) : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
        dispatcherProvider = CoroutineDispatcherProvider(
            io = testDispatcher,
            main = testDispatcher,
            default = testDispatcher
        )
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}
