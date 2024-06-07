package com.batterb.data.auth.impl.datasource

interface IAuthorizationDataSource {
    suspend fun requestAuthorization(login: String, password: String): LoginResponseDto
}