package com.appwork.porductvt.features.feeds

import com.appwork.porductvt.mvi.UiEffect

sealed interface FeedsEffect : UiEffect {
    object NavigateToAddFeed : FeedsEffect
    object NavigateBack : FeedsEffect
    data class NavigateToFeed(val id: String): FeedsEffect
}
