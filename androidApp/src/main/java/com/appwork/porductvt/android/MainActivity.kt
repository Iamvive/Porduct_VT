package com.appwork.porductvt.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.appwork.porductvt.android.feeds.domain.FeedsViewModel
import com.appwork.porductvt.android.feeds.ui.RenderFeeds
import com.appwork.porductvt.feeds.ui.FeedsPresenter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var feedsViewModel: FeedsViewModel

    lateinit var presenter: FeedsPresenter

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
                    RenderFeeds(
                        feedsViewModel = feedsViewModel,
                        presenter = presenter,
                    )
                }
            }
        }
    }

    class FeedsPresenterImp
    @Inject
    constructor() : FeedsPresenter {
        override fun didTapAdd(): Flow<Unit> = emptyFlow()
        override fun didTapItem(): Flow<String> = flowOf("1")
        override fun didTapBack(): Flow<Unit> = emptyFlow()
        override fun didTapTopBack(): Flow<Unit> = emptyFlow()
    }
}
