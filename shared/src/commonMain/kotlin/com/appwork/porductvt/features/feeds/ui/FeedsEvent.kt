package com.appwork.porductvt.features.feeds.ui

import com.appwork.porductvt.mvi.UiEvent

sealed interface FeedsEvent : UiEvent {
    object AddTapEvent : FeedsEvent
    object BackTapEvent : FeedsEvent
    object BackPressedEvent : FeedsEvent
    data class FeedItemTapEvent(val id: String) : FeedsEvent
}