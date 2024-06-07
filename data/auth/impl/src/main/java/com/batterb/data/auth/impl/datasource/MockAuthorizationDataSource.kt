package com.batterb.data.auth.impl.datasource

import kotlinx.coroutines.delay
import javax.inject.Inject

class MockAuthorizationDataSource @Inject constructor(

) : IAuthorizationDataSource {

    companion object {
        const val DEFAULT_MOCK_DELAY_MILLIS = 2000L
        const val MOCK_USERNAME = "test@gmail.com"
        const val MOCK_PASSWORD = "12345"
    }

    override suspend fun requestAuthorization(login: String, password: String): LoginResponseDto {
        delay(DEFAULT_MOCK_DELAY_MILLIS)
        if (login == MOCK_USERNAME && password == MOCK_PASSWORD){
            return LoginResponseDto(token = "some token")
        }
        throw Throwable("Login Error")
    }
}