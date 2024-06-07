package com.batterb.domain.auth.api

import com.batterb.common.ActionResult
import com.batterb.user.model.TokenData
import kotlinx.coroutines.flow.Flow

interface IAuthUC {
    operator fun invoke(login: String, password: String): Flow<ActionResult<TokenData>>
}