package com.batterb.domain.auth.impl

import com.batterb.common.ActionResult
import com.batterb.data.auth.api.IAuthorizationRepository
import com.batterb.domain.auth.api.IAuthUC
import com.batterb.user.model.TokenData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUC @Inject constructor(
    private val authRepository: IAuthorizationRepository
) : IAuthUC {
    override fun invoke(
        login: String,
        password: String
    ): Flow<ActionResult<TokenData>> {
        return authRepository.authorize(login, password)
    }

}