package com.quick.app

import android.app.Application
import android.util.Log

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        Log.d("Application", "onCreate: $this")
    }

    companion object {
        lateinit var instance: MyApplication
    }
}