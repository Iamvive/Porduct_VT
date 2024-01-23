package com.appwork.porductvt.feeds.state

import com.appwork.porductvt.feeds.ui.Feed

data class FeedsState(
    val isLoading: Boolean,
    val feeds: List<Feed>?,
)
