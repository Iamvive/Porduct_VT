package com.appwork.porductvt.feeds.data

import com.appwork.porductvt.feeds.ui.Feed.FeedData
import com.appwork.porductvt.feeds.ui.Feed.FeedWithDate
import com.appwork.porductvt.utils.IdUtils
import com.appwork.porductvt.utils.asString

object FeedsData {
    fun getFeeds() = listOf(
        getFeedWithDate(),
        getFeedData(),
        getFeedData(),
        getFeedData(),
        getFeedData(),
        getFeedData(),
    )

    private fun getFeedWithDate() = FeedWithDate(
        id = IdUtils.getRandomIntID().asString(),
        date = "Today",
    )

    private fun getFeedData() = FeedData(
        id = IdUtils.getRandomIntID().asString(),
        title = "Today",
        description = "This is very first thought of the app",
        moreInfo = null,
    )
}