package com.appwork.porductvt.android.sampledata

import com.appwork.porductvt.features.feeds.ui.Feed

object FeedsData {
    fun getFeedData() = Feed.FeedData(
        id = "1",
        title = "First Thought",
        description = "Then, an activity or a fragment that " +
                "is annotated with @AndroidEntryPoint can get the " +
                "ViewModel instance as normal using ViewModelProvider " +
                "or the by viewModels() KTX extensions:",
        moreInfo = null,
    )

    fun getFeedDate() = Feed.FeedWithDate(
        id = "2",
        date = "Today"
    )
}