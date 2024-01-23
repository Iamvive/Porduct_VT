package com.appwork.porductvt.feeds.ui

import kotlinx.coroutines.flow.Flow

interface FeedsPresenter {
    fun didTapAdd(): Flow<Unit>
    fun didTapItem(): Flow<String>
    fun didTapBack(): Flow<Unit>
    fun didTapTopBack(): Flow<Unit>
}