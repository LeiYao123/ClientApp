package com.quick.app.util

import android.os.Process

object ProcessUtil {
    fun killApp() {
        Process.killProcess(Process.myPid())
    }
}


object ResourceUtil {
    fun r(uri: String): String {
        if (uri.startsWith("http")) {
            return uri
        }
        return "https://rs.ixuea.com/quick/$uri"
    }
}