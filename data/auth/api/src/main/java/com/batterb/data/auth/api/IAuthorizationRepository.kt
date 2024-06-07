package com.batterb.data.auth.api

import com.batterb.common.ActionResult
import com.batterb.user.model.TokenData
import kotlinx.coroutines.flow.Flow

fun interface IAuthorizationRepository {
    fun authorize(login: String, password: String): Flow<ActionResult<TokenData>>
}