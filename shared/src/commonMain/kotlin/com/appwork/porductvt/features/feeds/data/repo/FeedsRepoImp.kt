package com.appwork.porductvt.features.feeds.data.repo

import com.appwork.porductvt.features.feeds.data.datasource.FeedsDataSource
import com.appwork.porductvt.features.feeds.ui.Feed

class FeedsRepoImp(
    private val dataSource: FeedsDataSource,
) : FeedsRepo {

    override suspend fun fetchFeeds(): List<Feed> =
        dataSource.fetchFeeds()
}