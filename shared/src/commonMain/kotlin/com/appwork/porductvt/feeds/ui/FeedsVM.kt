package com.appwork.porductvt.feeds.ui

import com.appwork.porductvt.feeds.data.FeedsData

data class FeedsVM(
    val feeds: List<Feed> = FeedsData.getFeeds()
)
