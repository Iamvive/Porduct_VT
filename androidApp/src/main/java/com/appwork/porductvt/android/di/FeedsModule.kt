package com.appwork.porductvt.android.di

import com.appwork.porductvt.android.MainActivity.FeedsPresenterImp
import com.appwork.porductvt.feeds.ui.FeedsPresenter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FeedsModule {

    @Binds
    internal abstract fun provideFeedsPresenter(
        imp: FeedsPresenterImp,
    ): FeedsPresenter
}