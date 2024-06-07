package com.batterb.messenger.di

import com.batterb.data.auth.api.IAuthorizationRepository
import com.batterb.data.auth.impl.AuthorizationRepository
import com.batterb.data.auth.impl.datasource.IAuthorizationDataSource
import com.batterb.data.auth.impl.datasource.MockAuthorizationDataSource
import com.batterb.domain.auth.api.IAuthUC
import com.batterb.domain.auth.impl.AuthUC
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {

    @Binds
    @Singleton
    fun bindAuthorizationDataSource(
        authorizationDataSourceImpl: MockAuthorizationDataSource
    ): IAuthorizationDataSource

    @Binds
    @Singleton
    fun bindAuthorizationRepository(
        authorizationRepositoryImpl: AuthorizationRepository
    ): IAuthorizationRepository

    @Binds
    fun bindAuthUC(
        authUC: AuthUC
    ): IAuthUC
}