package com.appwork.porductvt.android.feeds.domain

import com.appwork.porductvt.features.feeds.domain.FeedsViewModelMP
import com.appwork.porductvt.features.feeds.usecases.FeedsUseCase
import javax.inject.Inject

class FeedsViewModel
@Inject
constructor(
    feedsUseCase: FeedsUseCase
) : FeedsViewModelMP(feedsUseCase) {

    fun onCleared() {
        onClear()
    }
}