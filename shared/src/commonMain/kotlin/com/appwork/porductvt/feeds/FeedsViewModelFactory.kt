package com.appwork.porductvt.feeds

import com.appwork.porductvt.feeds.data.repo.FeedsRepoImp
import com.appwork.porductvt.feeds.domain.FeedsViewModelMP
import kotlinx.coroutines.CoroutineScope

object FeedsViewModelFactory {
    fun create(
        coroutineScope: CoroutineScope,
    ): FeedsViewModelMP = FeedsViewModelMP(
        scope = coroutineScope,
        feedsRepo = FeedsRepoImp(),
    )
}