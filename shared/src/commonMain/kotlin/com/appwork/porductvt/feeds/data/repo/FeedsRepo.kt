package com.appwork.porductvt.feeds.data.repo

import com.appwork.porductvt.feeds.ui.Feed
import com.appwork.porductvt.feeds.data.FeedsData
import kotlinx.coroutines.delay

interface FeedsRepo {
    suspend operator fun invoke(): List<Feed>
}

class FeedsRepoImp : FeedsRepo {
    override suspend operator fun invoke(): List<Feed> {
        delay(DELAY)
        return FeedsData.getFeeds()
    }

    companion object {
        const val DELAY = 2000L
    }
}