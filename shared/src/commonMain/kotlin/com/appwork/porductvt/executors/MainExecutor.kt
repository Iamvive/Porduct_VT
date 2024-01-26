package com.appwork.porductvt.executors

import com.appwork.porductvt.commons.AppDispatchers
import com.appwork.porductvt.commons.AppDispatchers.ioDispatcher
import com.appwork.porductvt.commons.AppDispatchers.mainDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class MainExecutor : CoroutinesCancellation, CoroutineScope {

    private val job = SupervisorJob()
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Handle $exception in CoroutineExceptionHandler")
    }

    override fun cancel() {
        job.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = job + AppDispatchers.defaultDispatcher + coroutineExceptionHandler

    fun <T> collectIoSafe(
        flow: Flow<T>,
        collectData: (T) -> Unit
    ) {
        launch {
            flow
                .flowOn(ioDispatcher)
                .distinctUntilChanged()
                .collect { value -> collectData(value) }
        }
    }

    fun <T> collectMainSafe(
        flow: Flow<T>,
        collectData: (T) -> Unit
    ) {
        launch {
            flow
                .flowOn(mainDispatcher)
                .distinctUntilChanged()
                .collect { value -> collectData(value) }
        }
    }
}