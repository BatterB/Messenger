package com.batterb.domain.user.impl

import com.batterb.domain.user.api.ISaveUserUC
import com.batterb.user.api.IUserRepository
import com.batterb.user.model.User
import javax.inject.Inject

class SaveUserUC @Inject constructor(
    private val userRepository: IUserRepository
) : ISaveUserUC {
    override suspend fun invoke(user: User) {
        userRepository.saveUser(user)
    }
}