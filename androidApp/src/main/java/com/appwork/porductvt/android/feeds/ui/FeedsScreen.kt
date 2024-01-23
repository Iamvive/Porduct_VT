package com.appwork.porductvt.android.feeds.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appwork.porductvt.android.feeds.domain.FeedsViewModel
import com.appwork.porductvt.feeds.state.FeedsState
import com.appwork.porductvt.feeds.ui.Feed.FeedData
import com.appwork.porductvt.feeds.ui.Feed.FeedWithDate
import com.appwork.porductvt.feeds.ui.FeedsPresenter

@Composable
fun RenderFeeds(
    feedsViewModel: FeedsViewModel,
    presenter: FeedsPresenter,
) {
    val feedState = feedsViewModel.feedsUIState.collectAsState(
        initial = FeedsState(
            isLoading = true,
            feeds = null
        )
    )

    feedState.value.feeds?.let {
        LazyColumn {
            items(it) { feed ->
                when (feed) {
                    is FeedData -> RenderFeedItem(feed = feed, presenter = presenter)
                    is FeedWithDate -> RenderDateView(title = feed.text)
                }
            }
        }
    } ?: RenderEmptyView()
}

@Composable
fun RenderEmptyView(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.fillMaxSize(),
        text = "Add your thoughts",
        style = MaterialTheme.typography.h3,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun RenderFeedItem(
    modifier: Modifier = Modifier,
    feed: FeedData,
    presenter: FeedsPresenter,
) {
    Box(modifier = modifier.clickable { presenter.didTapItem() }) {
        Card(
            elevation = 16.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column {
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = feed.text,
                    style = MaterialTheme.typography.h6,
                )
                Spacer(modifier = modifier.padding(2.dp))
                Text(
                    text = feed.description,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = modifier.padding(2.dp))
                feed.moreInfo?.let {
                    Text(
                        text = it.reference,
                        modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
                    )
                }
            }
        }
    }
}

@Composable
fun RenderDateView(
    modifier: Modifier = Modifier,
    title: String,
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        text = title,
        maxLines = 1,
        fontSize = 14.sp,
    )
}