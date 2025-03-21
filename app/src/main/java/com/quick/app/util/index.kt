package com.quick.app.util

import android.os.Process

object ProcessUtil {
    fun killApp() {
        Process.killProcess(Process.myPid())
    }
}