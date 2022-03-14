package com.example.twitter_trending_android.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitter_trending_android.data.repository.TwitterRepository
import com.example.twitter_trending_android.presentation.mapper.ViewStateMapper.mapTableDataToViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val twitterRepo: TwitterRepository
) : ViewModel() {

    private val _trends: MutableStateFlow<List<TrendViewState>> = MutableStateFlow(listOf())

    val trends: StateFlow<List<TrendViewState>> = _trends.asStateFlow()

    init {
        viewModelScope.launch {
            twitterRepo.getTrends().map {
                mapTableDataToViewState(it.data ?: listOf())
            }.flowOn(Dispatchers.IO).collect { trendViewStateList ->
                _trends.update {
                    trendViewStateList
                }
            }
        }
    }

    fun loadTrends() {
        viewModelScope.launch {
            twitterRepo.loadTrendsFromApi()
        }
    }
}
