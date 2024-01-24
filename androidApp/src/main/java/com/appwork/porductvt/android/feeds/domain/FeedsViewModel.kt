package com.appwork.porductvt.android.feeds.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appwork.porductvt.commons.AppDispatchers
import com.appwork.porductvt.feeds.FeedsViewModelFactory
import com.appwork.porductvt.feeds.state.FeedsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class FeedsViewModel
//@Inject
constructor() : ViewModel() {

    private val _feedsUIState: MutableStateFlow<FeedsState> =
        MutableStateFlow(FeedsState(isLoading = false, feeds = null))

    val feedsUIState: Flow<FeedsState>
        get() = _feedsUIState

    private val feedsViewModel = FeedsViewModelFactory.create(
        coroutineScope = viewModelScope,
    )

    init {
        feedsViewModel.init()
        viewModelScope.launch(AppDispatchers.ioDispatcher) { HandleStateStream().invoke() }
    }

    fun fetchFeeds() {
        feedsViewModel.fetchFeeds()
    }

    inner class HandleStateStream {
        suspend fun invoke() {
            feedsViewModel.feedsState
                .distinctUntilChanged()
                .collect { _feedsUIState.tryEmit(it) }
        }
    }
}