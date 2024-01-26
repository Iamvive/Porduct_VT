package com.appwork.porductvt.features.feeds.domain

import com.appwork.porductvt.features.feeds.FeedsEffect
import com.appwork.porductvt.features.feeds.FeedsEffect.NavigateBack
import com.appwork.porductvt.features.feeds.FeedsEffect.NavigateToAddFeed
import com.appwork.porductvt.features.feeds.FeedsEffect.NavigateToFeed
import com.appwork.porductvt.features.feeds.state.FeedsState
import com.appwork.porductvt.features.feeds.ui.FeedsEvent
import com.appwork.porductvt.features.feeds.ui.FeedsEvent.AddTapEvent
import com.appwork.porductvt.features.feeds.ui.FeedsEvent.BackPressedEvent
import com.appwork.porductvt.features.feeds.ui.FeedsEvent.BackTapEvent
import com.appwork.porductvt.features.feeds.ui.FeedsEvent.FeedItemTapEvent
import com.appwork.porductvt.features.feeds.usecases.FeedsUseCase
import com.appwork.porductvt.mvi.ShareViewModel

open class FeedsViewModelMP(
    private val feedsUseCase: FeedsUseCase,
) : ShareViewModel<FeedsState, FeedsEvent, FeedsEffect>() {

    init {
        fetchFeeds()
    }

    fun fetchFeeds() {
        setState { copy(isLoading = true) }

        collectIoSafe(feedsUseCase()) { result ->
            result
                .onSuccess {
                    setState { copy(isLoading = false, feeds = it) }
                }
                .onFailure {
                    setState { copy(isLoading = false, feeds = null) }
                    println("Exception while fetching the feeds is $it")
                }
        }
    }

    override fun getInitialState(): FeedsState = FeedsState(
        isLoading = false, feeds = null,
    )

    override fun onEvent(event: FeedsEvent) {
        val effect = when (event) {
            AddTapEvent -> NavigateToAddFeed
            BackPressedEvent, BackTapEvent -> NavigateBack
            is FeedItemTapEvent -> NavigateToFeed(id = event.id)
        }
        setEffect { effect }
    }

    /**
     * This will cancel all the coroutine job running for this view model
     */
    override fun onClear() {
        cancel()
    }
}