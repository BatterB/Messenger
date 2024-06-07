package com.batterb.data.auth.impl.datasource.mappers

import com.batterb.data.auth.impl.datasource.LoginResponseDto
import com.batterb.user.model.TokenData
import javax.inject.Inject

class LoginResponseToEntityMapper @Inject constructor() {
    fun map(from: LoginResponseDto): TokenData = TokenData(token = from.token)
}