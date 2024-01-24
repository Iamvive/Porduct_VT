package com.appwork.porductvt.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.appwork.porductvt.android.feeds.domain.FeedsViewModel
import com.appwork.porductvt.android.feeds.ui.RenderFeeds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppActivity : ComponentActivity() {

    private val feedsViewModel: FeedsViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        feedsViewModel.fetchFeeds()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RenderFeeds(
                        feedsViewModel = feedsViewModel,
                        didTapFeedItem = { didTapFeedItem(it) },
                    )
                }
            }
        }
    }

    private fun didTapFeedItem(id: String) {
        println("Item with id $id is tapped")
    }
}
