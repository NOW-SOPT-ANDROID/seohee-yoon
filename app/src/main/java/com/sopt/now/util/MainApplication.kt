package com.sopt.now.util

import android.app.Application

class MainApplication : Application() { // Activity보다 먼저 생성해야 다른 곳에 데이터를 넘길 수 있음
    companion object {
        lateinit var prefsManager: PreferencesManager
    }

    override fun onCreate() {
        prefsManager = PreferencesManager(applicationContext)
        super.onCreate()
    }
}