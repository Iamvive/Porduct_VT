package com.appwork.porductvt.features.feeds.data.repo

import com.appwork.porductvt.features.feeds.ui.Feed

interface FeedsRepo {
    suspend fun fetchFeeds(): List<Feed>
}
