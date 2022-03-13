package com.example.twitter_trending_android.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twitter_trending_android.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TrendAdapter()

        binding.trendListView.adapter = adapter
        binding.trendListView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.trends.collect {
                    adapter.submitList(it)
                }
            }
        }

        binding.swipeToRefreshLayout.setOnRefreshListener {
            //TODO Remove this
            lifecycleScope.launch {
                delay(2000)
                binding.swipeToRefreshLayout.isRefreshing = false
            }
            viewModel.loadTrends()
        }

        viewModel.loadTrends()

    }
}
