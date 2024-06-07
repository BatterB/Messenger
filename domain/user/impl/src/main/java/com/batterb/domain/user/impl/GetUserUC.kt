package com.batterb.domain.user.impl

import com.batterb.domain.user.api.IGetUserUC
import com.batterb.user.api.IUserRepository
import com.batterb.user.model.User
import javax.inject.Inject

class GetUserUC @Inject constructor(
    private val userRepository: IUserRepository
) : IGetUserUC {
    override suspend fun invoke(): User? {
        return userRepository.getUser()
    }
}