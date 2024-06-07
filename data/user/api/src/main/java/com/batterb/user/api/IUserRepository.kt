package com.batterb.user.api

import com.batterb.user.model.User

interface IUserRepository {
    suspend fun saveUser(user: User)
    suspend fun deleteUser()
    suspend fun getUser(): User?
}