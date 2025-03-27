package com.quick.app.extension

import android.widget.Toast
import com.quick.app.MyApplication

/**
 * String 扩展toast
 */

/**
 * 短toast
 */
fun String.shortToast() {
    Toast.makeText(MyApplication.instance, this, Toast.LENGTH_SHORT).show()
}

/**
 * 长toast
 */
fun String.longToast() {
    Toast.makeText(MyApplication.instance, this, Toast.LENGTH_LONG).show()
}