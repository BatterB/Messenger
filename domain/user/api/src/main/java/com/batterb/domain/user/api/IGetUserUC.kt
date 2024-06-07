package com.batterb.domain.user.api

import com.batterb.user.model.User

interface IGetUserUC {
    suspend operator fun invoke(): User?
}