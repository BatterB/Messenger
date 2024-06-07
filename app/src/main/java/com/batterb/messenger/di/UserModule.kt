package com.batterb.messenger.di

import androidx.datastore.core.Serializer
import com.batterb.domain.user.api.IDeleteUserUC
import com.batterb.domain.user.api.IGetUserUC
import com.batterb.domain.user.api.ISaveUserUC
import com.batterb.domain.user.impl.DeleteUserUC
import com.batterb.domain.user.impl.GetUserUC
import com.batterb.domain.user.impl.SaveUserUC
import com.batterb.user.api.IUserRepository
import com.batterb.user.impl.UserRepository
import com.batterb.user.impl.UserSerializer
import com.batterb.user.impl.datasource.IUserDataSource
import com.batterb.user.impl.datasource.UserDataSource
import com.batterb.user.impl.datasource.dto.UserDto
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UserModule {

    @Binds
    @Singleton
    fun bindUserDataSource(
        userDataSource: UserDataSource
    ): IUserDataSource

    @Binds
    @Singleton
    fun bindUserRepository(
        userRepositoryImpl: UserRepository
    ): IUserRepository

    @Binds
    fun bindDeleteUserUC(
        uc: DeleteUserUC
    ) : IDeleteUserUC

    @Binds
    fun bindGetUserUC(
        uc: GetUserUC
    ) : IGetUserUC

    @Binds
    fun bindSaveUserUC(
        uc: SaveUserUC
    ) : ISaveUserUC

    @Binds
    fun bindUserSerializer(userSerializerImpl: UserSerializer) : Serializer<UserDto?>
}