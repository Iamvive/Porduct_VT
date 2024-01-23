package com.appwork.porductvt.feeds.ui

sealed class Feed(
    open val id: String,
    open val text: String,
) {
    data class FeedWithDate(
        override val id: String,
        val date: String,
    ) : Feed(id = id, text = date)

    data class FeedData(
        override val id: String,
        val title: String,
        val description: String,
        val moreInfo: FeedInfo?,
    ) : Feed(id = id, text = title)
}

data class FeedInfo(
    val reference: String,
)