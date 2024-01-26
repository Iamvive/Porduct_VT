package com.appwork.porductvt.features.feeds

import com.appwork.porductvt.features.feeds.data.datasource.FeedsDataSource
import com.appwork.porductvt.features.feeds.data.datasource.FeedsRemoteDataSource
import com.appwork.porductvt.features.feeds.data.repo.FeedsRepo
import com.appwork.porductvt.features.feeds.data.repo.FeedsRepoImp
import com.appwork.porductvt.features.feeds.usecases.FeedsUseCase

object FeedsViewModelFactory {
    fun create() = getFeedsUseCase()

    private fun getFeedsUseCase(): FeedsUseCase =
        FeedsUseCase(repo = getFeedsRepo())

    private fun getFeedsRepo(): FeedsRepo =
        FeedsRepoImp(dataSource = getFeedsDataSource())

    private fun getFeedsDataSource(): FeedsDataSource =
        FeedsRemoteDataSource()
}