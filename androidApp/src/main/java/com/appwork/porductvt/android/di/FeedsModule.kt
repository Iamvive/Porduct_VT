package com.appwork.porductvt.android.di

import com.appwork.porductvt.commons.AppDispatchers.defaultDispatcher
import com.appwork.porductvt.feeds.data.repo.FeedsRepo
import com.appwork.porductvt.feeds.data.repo.FeedsRepoImp
import com.appwork.porductvt.feeds.domain.FeedsViewModelMP
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(ActivityComponent::class)
object FeedsModule {

    @Provides
    internal fun bindsFeedsRepo(): FeedsRepo = FeedsRepoImp()

    @Provides
    fun provideFeedsViewModelMP(
        scope: CoroutineScope,
        feedsRepo: FeedsRepo,
    ): FeedsViewModelMP = FeedsViewModelMP(scope, feedsRepo)

    @Provides
    fun provideCoroutineContext(): CoroutineScope =
        CoroutineScope(SupervisorJob() + defaultDispatcher)
}