package com.batterb.user.impl

import com.batterb.user.impl.datasource.dto.UserDto
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class UserSerializer @Inject constructor(
    private val cryptoManager: CryptoManager
) : IUserSerializer {
    override val defaultValue: UserDto?
        get() = null

    override suspend fun readFrom(input: InputStream): UserDto? {
        val decryptedBytes = cryptoManager.decrypt(input)
        return try {
            Json.decodeFromString(
                deserializer = UserDto.serializer(),
                string = decryptedBytes.decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: UserDto?, output: OutputStream) {
        t?.let{ user ->
            cryptoManager.encrypt(
                bytes = Json.encodeToString(
                    serializer = UserDto.serializer(),
                    value = user
                ).encodeToByteArray(),
                outputStream = output
            )
        }

    }
}