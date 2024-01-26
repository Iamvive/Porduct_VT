package com.appwork.porductvt.mvi

import com.appwork.porductvt.executors.MainExecutor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class ShareViewModel<State : UiState, Event : UiEvent, Effect : UiEffect> :
    MainExecutor() {
    private val initialSate: State by lazy { getInitialState() }

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialSate)
    val uiState = _uiState.asStateFlow()
    val currentState: State = uiState.value

    private val _uiEvent: MutableSharedFlow<Event> = MutableSharedFlow(replay = 1)
    val uiEvent = _uiEvent.asSharedFlow()

    private val _uiEffect: Channel<Effect> = Channel()
    val uiEffect: Flow<Effect> = _uiEffect.receiveAsFlow()

    init {
        launch { EventsHandler().invoke() }
    }

    abstract fun getInitialState(): State

    abstract fun onClear()

    // TODO  check for the failure of event throwing an exception and again do the event
    inner class EventsHandler {
        suspend fun invoke() {
            uiEvent.distinctUntilChanged()
                .collect { onEvent(it) }
        }
    }

    abstract fun onEvent(event: Event)

    fun setEvent(event: Event) {
        _uiEvent.tryEmit(event)
    }

    protected fun setState(reducer: State.() -> State) {
        val newState = currentState.reducer()
        _uiState.tryEmit(newState)
    }

    protected fun setEffect(builder: () -> Effect) {
        _uiEffect.trySend(builder())
    }
}