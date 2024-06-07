package com.batterb.user.impl.datasource

import com.batterb.user.impl.datasource.dto.UserDto

interface IUserDataSource {
    suspend fun saveUser(user: UserDto)
    suspend fun deleteUser()
    suspend fun getSignedInUser(): UserDto?
}
