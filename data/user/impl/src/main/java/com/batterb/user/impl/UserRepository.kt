package com.batterb.user.impl

import com.batterb.user.api.IUserRepository
import com.batterb.user.impl.datasource.UserDataSource
import com.batterb.user.impl.datasource.mappers.UserDtoToUserEntityMapper
import com.batterb.user.impl.datasource.mappers.UserEntityToUserDtoMapper
import com.batterb.user.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDataSource: UserDataSource,
    private val userDtoToEntityMapper: UserDtoToUserEntityMapper,
    private val userEntityToDtoMapper: UserEntityToUserDtoMapper
) : IUserRepository {
    override suspend fun saveUser(user: User) {
        userDataSource.saveUser(userEntityToDtoMapper.map(user))
    }

    override suspend fun deleteUser() {
        userDataSource.deleteUser()
    }

    override suspend fun getUser(): User? {
        val user = userDataSource.getSignedInUser()
        return user?.let { userDtoToEntityMapper.map(user) }
    }

}