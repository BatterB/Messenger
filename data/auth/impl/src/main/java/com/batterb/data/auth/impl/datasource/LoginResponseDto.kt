package com.batterb.data.auth.impl.datasource

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class LoginResponseDto(
    @SerialName("token")
    val token: String
)
