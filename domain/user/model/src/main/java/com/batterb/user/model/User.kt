package com.batterb.user.model

import kotlinx.serialization.Serializable

@Serializable
data class User (
     val id: String,
     val login: String,
     val password: String,
     val token: TokenData
)