package com.example.twitter_trending_android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.twitter_trending_android.domain.repository.TwitterRepository
import com.example.twitter_trending_android.ui.mapper.ViewStateMapper.mapTableDataToViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val twitterRepo: TwitterRepository
) : ViewModel() {

    val trends: LiveData<List<TrendViewState>> = twitterRepo.getTrends().map { res ->
        mapTableDataToViewState(res.data!!)
    }.asLiveData()

    fun loadTrends() {
        viewModelScope.launch {
            twitterRepo.loadTrendsFromApi()
        }
    }
}
