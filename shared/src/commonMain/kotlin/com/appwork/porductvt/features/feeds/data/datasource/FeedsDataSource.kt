package com.appwork.porductvt.features.feeds.data.datasource

import com.appwork.porductvt.features.feeds.ui.Feed

interface FeedsDataSource {
    suspend fun fetchFeeds(): List<Feed>
}