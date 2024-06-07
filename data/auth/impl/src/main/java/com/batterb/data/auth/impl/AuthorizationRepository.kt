package com.batterb.data.auth.impl

import com.batterb.common.ActionResult
import com.batterb.common.dispatchers.DispatcherProvider
import com.batterb.common.switchType
import com.batterb.data.auth.api.IAuthorizationRepository
import com.batterb.data.auth.impl.datasource.IAuthorizationDataSource
import com.batterb.data.auth.impl.datasource.LoginResponseDto
import com.batterb.data.auth.impl.datasource.mappers.LoginResponseToEntityMapper
import com.batterb.user.model.TokenData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthorizationRepository @Inject constructor(
    private val authorizationDataSource: IAuthorizationDataSource,
    private val loginResponseToEntityMapper: LoginResponseToEntityMapper,
    private val dispatcherProvider: DispatcherProvider
) : IAuthorizationRepository {
    override fun authorize(
        login: String,
        password: String
    ): Flow<ActionResult<TokenData>> = flow<ActionResult<LoginResponseDto>> {
        emit(ActionResult.Loading(message = "Loading"))
        authorizationDataSource
            .runCatching {
                requestAuthorization(login, password)
            }
            .onFailure { loginError ->
                emit(ActionResult.Error(loginError))
            }
            .onSuccess { token ->
                emit(ActionResult.Success(token))
            }
    }
        .flowOn(dispatcherProvider.io)
        .switchType{ loginResponseDto ->  loginResponseToEntityMapper.map(loginResponseDto) }
        .flowOn(dispatcherProvider.computation)
}