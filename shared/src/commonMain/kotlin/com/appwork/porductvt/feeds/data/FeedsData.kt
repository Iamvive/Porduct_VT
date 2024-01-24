package com.appwork.porductvt.feeds.data

import com.appwork.porductvt.feeds.ui.Feed.FeedData
import com.appwork.porductvt.feeds.ui.Feed.FeedWithDate
import com.appwork.porductvt.feeds.ui.FeedInfo
import com.appwork.porductvt.utils.IdUtils
import com.appwork.porductvt.utils.asString
import kotlin.random.Random

object FeedsData {
    fun getFeeds() = listOf(
        getFeedWithDate(),
        getFeedData(),
        getFeedData(),
        getFeedData(),
        getFeedWithDate(),
        getFeedData(),
        getFeedData(),
        getFeedWithDate(),
        getFeedData(),
        getFeedData(),
        getFeedWithDate(),
        getFeedData(),
        getFeedData(),
    )

    private fun getFeedWithDate(): FeedWithDate {
        val number = Random.nextInt()
        return FeedWithDate(
            id = IdUtils.getRandomIntID().asString(),
            date = if (number % 2 == 0) "Today" else "Not Today",
        )
    }

    private fun getFeedData() = FeedData(
        id = IdUtils.getRandomIntID().asString(),
        title = "Thought",
        description = "Then, an activity or a fragment that " +
                "is annotated with @AndroidEntryPoint can get the " +
                "ViewModel instance as normal using ViewModelProvider " +
                "or the by viewModels() KTX extensions:",
        moreInfo = getMoreInfo(),
    )

    private fun getMoreInfo() = FeedInfo(
        reference = "https://developer.android.com/jetpack/compose/layouts/basics"
    )
}