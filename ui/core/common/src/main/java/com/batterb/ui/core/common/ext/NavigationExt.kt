package com.batterb.ui.core.common.ext

import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.registry.ScreenRegistry

fun ScreenProvider.getScreen() = ScreenRegistry.get(this)