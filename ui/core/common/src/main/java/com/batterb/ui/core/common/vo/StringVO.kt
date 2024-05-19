package com.batterb.ui.core.common.vo

import android.content.Context
import androidx.annotation.StringRes
import java.util.Locale

fun IStringVO.Companion.Plain(text: String): IStringVO = StringVO.Plain(text)

fun IStringVO.Companion.Resource(
    @StringRes resId: Int,
    vararg params: Any?
): IStringVO = StringVO.Resource(resId, *params)

fun IStringVO.Companion.Callback(
    callback: Context.() -> String
): IStringVO = StringVO.Callback(callback)

val IStringVO.Companion.EMPTY: IStringVO
    get() = emptyStringVO

internal val emptyStringVO = StringVO.Plain("")

internal sealed class StringVO : IStringVO {

    data class Plain(
        val text: String
    ) : StringVO() {
        override fun get(context: Context): String {
            return text
        }

        override fun get(context: Context, vararg params: Any?): String {
            return String.format(text, *params)
        }
    }

    class Resource(
        @StringRes val resId: Int,
        vararg val params: Any?
    ) : StringVO() {

        override fun get(context: Context): String {
            val p = params.map { param ->
                if (param is IStringVO) {
                    param.get(context)
                } else {
                    param
                }
            }.toTypedArray()

            return String.format(Locale.getDefault(), context.getString(resId), *p)
        }

        override fun get(context: Context, vararg params: Any?): String {
            val p = params.map { param ->
                if (param is IStringVO) {
                    param.get(context)
                } else {
                    param
                }
            }.toTypedArray()

            return String.format(Locale.getDefault(), context.getString(resId), *p)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Resource) return false

            if (resId != other.resId) return false
            if (!params.contentEquals(other.params)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = resId
            result = 31 * result + params.contentHashCode()
            return result
        }
    }

    data class Callback(
        private val callback: (Context) -> String
    ) : StringVO() {
        override fun get(context: Context): String {
            return callback(context)
        }

        override fun get(context: Context, vararg params: Any?): String {
            return String.format(callback(context), *params)
        }
    }
}
