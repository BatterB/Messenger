package com.batterb.domain.user.impl

import com.batterb.domain.user.api.IDeleteUserUC
import com.batterb.user.api.IUserRepository
import javax.inject.Inject

class DeleteUserUC @Inject constructor(
    private val userRepository: IUserRepository
) : IDeleteUserUC {
    override suspend fun invoke() {
        userRepository.deleteUser()
    }
}