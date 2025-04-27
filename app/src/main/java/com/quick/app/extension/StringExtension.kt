package com.quick.app.extension


fun String.safeToInt(default: Int = 0): Int = this.toIntOrNull() ?: default
