package com.appwork.porductvt.android.di

import com.appwork.porductvt.features.feeds.FeedsViewModelFactory
import com.appwork.porductvt.features.feeds.domain.FeedsViewModelMP
import com.appwork.porductvt.features.feeds.usecases.FeedsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object FeedsModule {
    @Provides
    internal fun provideFeedsUseCase(): FeedsUseCase =
        FeedsViewModelFactory.create()

    @Provides
    internal fun providesFeedsViewModel(
        useCase: FeedsUseCase,
    ) = FeedsViewModelMP(useCase)
}