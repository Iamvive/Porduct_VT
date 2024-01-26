package com.appwork.porductvt.features.feeds.data.datasource

import com.appwork.porductvt.features.feeds.data.FeedsData
import com.appwork.porductvt.features.feeds.ui.Feed
import kotlinx.coroutines.delay

class FeedsRemoteDataSource : FeedsDataSource {

    override suspend fun fetchFeeds(): List<Feed> {
        delay(DELAY)
        return FeedsData.getFeeds()
    }

    companion object {
        const val DELAY = 2000L
    }
}