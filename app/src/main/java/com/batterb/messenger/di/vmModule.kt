package com.batterb.messenger.di

import com.batterb.impl.ui.SplashViewModel
import com.batterb.ui.auth.impl.mvi.AuthorizationViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    @IntoSet
    @HiltViewModelMap.KeySet
    fun provideSplashViewModelKey(): String {
        return SplashViewModel::class.java.name
    }

    @Provides
    @IntoSet
    @HiltViewModelMap.KeySet
    fun providerAuthorizationViewModelKey(): String {
        return AuthorizationViewModel::class.java.name
    }
}