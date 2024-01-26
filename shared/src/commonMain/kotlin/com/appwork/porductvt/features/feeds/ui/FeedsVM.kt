package com.appwork.porductvt.features.feeds.ui

import com.appwork.porductvt.features.feeds.data.FeedsData

data class FeedsVM(
    val feeds: List<Feed> = FeedsData.getFeeds()
)
