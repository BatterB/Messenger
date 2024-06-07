package com.batterb.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed interface ActionResult<T> {
    data class Loading<T>(val message: String? = null) : ActionResult<T>
    data class Success<T>(val data: T) : ActionResult<T>
    data class Error<T>(val error: Throwable) : ActionResult<T>
}

fun <FROM, TO> ActionResult<FROM>.switchType(map: ((FROM) -> TO)? = null): ActionResult<TO> =
    when (this) {
        is ActionResult.Error -> ActionResult.Error(error)
        is ActionResult.Loading -> ActionResult.Loading(message)
        is ActionResult.Success -> ActionResult.Success(
            map?.invoke(data) ?: throw NotImplementedError("Success mapper not implemented")
        )
    }

fun <FROM, TO> Flow<ActionResult<FROM>>.switchType(map: ((FROM) -> TO)? = null): Flow<ActionResult<TO>> =
    map { it.switchType(map) }