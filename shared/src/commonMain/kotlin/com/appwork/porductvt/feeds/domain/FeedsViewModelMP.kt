package com.appwork.porductvt.feeds.domain

import com.appwork.porductvt.commons.AppDispatchers.defaultDispatcher
import com.appwork.porductvt.commons.AppDispatchers.ioDispatcher
import com.appwork.porductvt.feeds.data.repo.FeedsRepo
import com.appwork.porductvt.feeds.state.FeedsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FeedsViewModelMP(
    private val scope: CoroutineScope,
    private val feedsRepo: FeedsRepo,
) {

    fun init() {
        scope.launch(defaultDispatcher) { AddTapHandler().invoke() }
        scope.launch(defaultDispatcher) { ItemTapHandler().invoke() }
        scope.launch(defaultDispatcher) { BackTapHandler().invoke() }
        scope.launch(defaultDispatcher) { TopBackTapHandler().invoke() }
    }

    private val _feedsState: MutableStateFlow<FeedsState> =
        MutableStateFlow(FeedsState(isLoading = false, feeds = null))

    val feedsState: Flow<FeedsState>
        get() = _feedsState

    fun fetchFeeds() {
        scope.launch(ioDispatcher) {
            val feeds = feedsRepo()
            val currentState = _feedsState.value
            _feedsState.tryEmit(currentState.copy(isLoading = false, feeds = feeds))
        }
    }

    private inner class AddTapHandler {
        suspend fun invoke() {
//            presenter.didTapAdd()
//                .distinctUntilChanged()
//                .collect {
//                    println("feedsViewModel inside AddTapHandler")
//                }
        }
    }

    private inner class ItemTapHandler {
        suspend fun invoke() {
//            presenter.didTapItem()
//                .distinctUntilChanged()
//                .collect {
//                    println("feedsViewModel inside ItemTapHandler")
//                }
        }
    }

    private inner class BackTapHandler {
        suspend fun invoke() {
//            presenter.didTapBack()
//                .distinctUntilChanged()
//                .collect {
//                    println("feedsViewModel inside BackTapHandler")
//                }
        }
    }

    private inner class TopBackTapHandler {
        suspend fun invoke() {
//            presenter.didTapTopBack()
//                .distinctUntilChanged()
//                .collect {
//                    println("FeedsViewModelMP inside TopBackTapHandler")
//                }
        }
    }
}