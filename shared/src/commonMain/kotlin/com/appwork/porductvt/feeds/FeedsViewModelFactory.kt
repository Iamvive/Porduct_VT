package com.appwork.porductvt.feeds

import com.appwork.porductvt.feeds.data.repo.FeedsRepoImp
import com.appwork.porductvt.feeds.domain.FeedsViewModelMP
import com.appwork.porductvt.feeds.ui.FeedsPresenter
import kotlinx.coroutines.CoroutineScope

object FeedsViewModelFactory {
    fun create(
        coroutineScope: CoroutineScope,
        presenter: FeedsPresenter,
    ): FeedsViewModelMP = FeedsViewModelMP(
        scope = coroutineScope,
        presenter = presenter,
        feedsRepo = FeedsRepoImp(),
    )
}