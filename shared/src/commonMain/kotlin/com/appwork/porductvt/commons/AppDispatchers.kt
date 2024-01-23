package com.appwork.porductvt.commons

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

object AppDispatchers {
    val mainDispatcher = Dispatchers.Main
    val ioDispatcher = Dispatchers.IO
    val defaultDispatcher = Dispatchers.Default
    val unconfinedDispatcher = Dispatchers.Unconfined
}