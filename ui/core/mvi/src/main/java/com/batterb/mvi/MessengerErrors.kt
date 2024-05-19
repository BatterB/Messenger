package com.batterb.mvi

import android.content.Context
import com.batterb.ui.core.common.ext.asVO
import com.batterb.ui.core.common.vo.IStringVO

abstract class MessengerErrors(cause: Throwable?) : Throwable(cause) {

    protected open val errorCode: IStringVO = this::class.java.simpleName.asVO()

    protected abstract val description: IStringVO

    open fun getDescription(context: Context): String = description.get(context)

    open fun getDescriptionWithCode(context: Context): String = StringBuilder().apply {
        appendLine(getDescription(context))
        append(errorCode.get(context))
    }.toString()

    open fun getTitle(context: Context): String = errorCode.get(context)

}

class UnhandledError(override val cause: Throwable) : MessengerErrors(cause) {
    override val description: IStringVO = (cause.message ?: cause::class.java.simpleName).asVO()
}

fun Throwable.extractError(): MessengerErrors = this as? MessengerErrors ?: UnhandledError(this)