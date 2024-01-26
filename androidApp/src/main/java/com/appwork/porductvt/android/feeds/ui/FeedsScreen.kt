package com.appwork.porductvt.android.feeds.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.appwork.porductvt.android.sampledata.FeedsData
import com.appwork.porductvt.features.feeds.FeedsEffect
import com.appwork.porductvt.features.feeds.FeedsEffect.NavigateBack
import com.appwork.porductvt.features.feeds.FeedsEffect.NavigateToAddFeed
import com.appwork.porductvt.features.feeds.FeedsEffect.NavigateToFeed
import com.appwork.porductvt.features.feeds.state.FeedsState
import com.appwork.porductvt.features.feeds.ui.Feed
import com.appwork.porductvt.features.feeds.ui.Feed.FeedData
import com.appwork.porductvt.features.feeds.ui.Feed.FeedWithDate
import com.appwork.porductvt.features.feeds.ui.FeedsEvent
import com.appwork.porductvt.features.feeds.ui.FeedsEvent.FeedItemTapEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RenderFeeds(
    stateStream: StateFlow<FeedsState>,
    onUiEvent: (FeedsEvent) -> Unit,
    effectStream: Flow<FeedsEffect>,
) {
    val feedState by stateStream.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        effectStream.collectLatest { effect ->
            executeEffect(effect)
        }
    }

    feedState.feeds?.let {
        RenderFeedsListColumn(
            feeds = it,
        ) { id -> onUiEvent(FeedItemTapEvent(id = id)) }
    } ?: RenderEmptyView()
}

private fun executeEffect(effect: FeedsEffect) {
    when (effect) {
        NavigateBack -> println("Feeds screen current effect : $NavigateBack")
        NavigateToAddFeed -> println("Feeds screen current effect : $NavigateToAddFeed")
        is NavigateToFeed -> println("Feeds screen current effect : NavigateToFeed and id is ${effect.id}")
    }
}

@Composable
private fun RenderFeedsListColumn(
    feeds: List<Feed>,
    didTapFeedItem: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        items(feeds) { feed -> RenderFeedsByType(feed, didTapFeedItem) }
    }
}

@Composable
private fun RenderFeedsByType(
    feed: Feed,
    didTapFeedItem: (String) -> Unit,
) {
    when (feed) {
        is FeedData -> RenderFeedItem(feed = feed, didTapFeedItem = didTapFeedItem)
        is FeedWithDate -> RenderDateView(title = feed.text)
    }
}

@Preview
@Composable
fun RenderEmptyView(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.wrapContentSize(),
        text = "Add your thoughts",
        style = MaterialTheme.typography.h5,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun RenderFeedItem(
    modifier: Modifier = Modifier,
    feed: FeedData = FeedsData.getFeedData(),
    didTapFeedItem: (String) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp)
            .clickable { didTapFeedItem(feed.id) }
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(6.dp),
        ) {
            Column(
                modifier = modifier.padding(4.dp),
            ) {
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
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                        style = MaterialTheme.typography.body2,
                        color = Color.Blue,
                        fontStyle = FontStyle.Italic,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RenderDateView(
    modifier: Modifier = Modifier,
    title: String = "Today",
) {
    Text(
        modifier = modifier
            .padding(4.dp)
            .wrapContentWidth(),
        text = title,
        textAlign = TextAlign.Center,
        maxLines = 1,
        color = Color.Black,
        style = MaterialTheme.typography.h6,
    )
}