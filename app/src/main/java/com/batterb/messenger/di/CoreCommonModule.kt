package com.batterb.messenger.di

import com.batterb.common.dispatchers.DispatcherProvider
import com.batterb.user.impl.UserSerializer
import com.batterb.user.impl.datasource.dto.UserDto
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import androidx.datastore.core.Serializer
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoreCommonModule {
    @Provides
    @Singleton
    fun provideCoroutineDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val io: CoroutineDispatcher = Dispatchers.IO
        override val computation: CoroutineDispatcher = Dispatchers.Default
        override val main: CoroutineDispatcher = Dispatchers.Main
        override val unconfined: CoroutineDispatcher = Dispatchers.Unconfined
    }



}