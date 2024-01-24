package com.appwork.porductvt.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.appwork.porductvt.android.feeds.domain.FeedsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppActivity : ComponentActivity() {

    lateinit var feedsViewModel: FeedsViewModel

    override fun onStart() {
        super.onStart()
        feedsViewModel.fetchFeeds()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        feedsViewModel = ViewModelProvider(this)[FeedsViewModel::class.java]
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    RenderFeeds(
//                        feedsViewModel = feedsViewModel,
//                    )
                }
            }
        }
    }
}
