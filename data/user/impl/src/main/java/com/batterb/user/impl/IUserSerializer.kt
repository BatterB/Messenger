package com.batterb.user.impl

import androidx.datastore.core.Serializer
import com.batterb.user.impl.datasource.dto.UserDto

interface IUserSerializer: Serializer<UserDto?>