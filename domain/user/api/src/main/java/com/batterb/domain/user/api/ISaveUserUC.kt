package com.batterb.domain.user.api

import com.batterb.user.model.User

interface ISaveUserUC {

    suspend operator fun invoke(user: User)

}