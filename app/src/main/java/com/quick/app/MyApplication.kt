package com.quick.app

import android.app.Application
import android.util.Log
import com.quick.app.data.PreferencesManager

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        PreferencesManager.init(this)
        Log.d("Application", "onCreate: $this")
    }

    companion object {
        lateinit var instance: MyApplication
    }
}