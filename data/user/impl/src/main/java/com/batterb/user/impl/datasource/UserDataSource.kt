package com.batterb.user.impl.datasource

import android.content.Context
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.batterb.user.impl.datasource.dto.UserDto
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first

import javax.inject.Inject

class UserDataSource @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val serializer: Serializer<UserDto>
) : IUserDataSource {

    private val Context.dataStore by dataStore(
        fileName = USER_DATA_STORE_FILE_NAME,
        serializer = serializer
    )

    private var currentUser: UserDto? = null

    override suspend fun saveUser(user: UserDto) {
        currentUser = user
        context.dataStore.updateData { user }
    }

    override suspend fun deleteUser() {
        context.dataStore.updateData { serializer.defaultValue }
        currentUser = null
    }

    override suspend fun getSignedInUser(): UserDto? {
        currentUser ?: pullCachedData()
        return currentUser.takeIf { it != serializer.defaultValue }
    }

    private suspend fun pullCachedData() {
        val user = context.dataStore.data.first()
        currentUser = user
    }

    companion object {
        const val USER_DATA_STORE_FILE_NAME = "user.json"
    }

}