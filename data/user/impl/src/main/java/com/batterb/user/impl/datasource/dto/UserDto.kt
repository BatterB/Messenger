package com.batterb.user.impl.datasource.dto


import com.batterb.user.model.TokenData
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String,
    val login: String,
    val password: String,
    val token: TokenData
)