package com.appwork.porductvt.features.feeds.state

import com.appwork.porductvt.features.feeds.ui.Feed
import com.appwork.porductvt.mvi.UiState

data class FeedsState(
    val isLoading: Boolean,
    val feeds: List<Feed>?,
): UiState
