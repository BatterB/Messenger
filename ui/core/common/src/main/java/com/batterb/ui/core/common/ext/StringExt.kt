package com.batterb.ui.core.common.ext

import com.batterb.ui.core.common.vo.IStringVO
import com.batterb.ui.core.common.vo.Plain

fun String.asVO(): IStringVO {
    return IStringVO.Plain(this)
}