package com.batterb.messenger

import android.app.Application
import com.batterb.ui.core.navigation.registerNavigationScreens
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MessengerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        registerNavigationScreens()
    }

}