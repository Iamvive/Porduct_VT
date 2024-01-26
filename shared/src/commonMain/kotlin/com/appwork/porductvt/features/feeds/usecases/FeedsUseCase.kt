package com.appwork.porductvt.features.feeds.usecases

import com.appwork.porductvt.features.feeds.data.repo.FeedsRepo
import kotlinx.coroutines.flow.flow

class FeedsUseCase(
    private val repo: FeedsRepo,
) {
    operator fun invoke() = flow {
        emit(
            try {
                Result.success(repo.fetchFeeds())
            } catch (e: Exception) {
                println("Exception is $e")
                Result.failure(e)
            }
        )
    }
}