package com.batterb.user.impl.datasource.mappers

import com.batterb.user.impl.datasource.dto.UserDto
import com.batterb.user.model.User
import javax.inject.Inject

class UserDtoToUserEntityMapper  @Inject constructor() {
    fun map (from: UserDto) = User(
        login = from.login,
        password = from.password,
        token = from.token,
        id = from.id
    )
}