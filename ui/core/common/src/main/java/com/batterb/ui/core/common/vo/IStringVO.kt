package com.batterb.ui.core.common.vo

import android.content.Context
import java.io.Serializable

interface IStringVO : Serializable {

    companion object

    fun get(context: Context, vararg params: Any?): String

    fun get(context: Context): String
}