package com.quick.app.feature.splash

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SplashViewModel: ViewModel() {
    private var timer: CountDownTimer? = null
    private val _timeLeft = MutableStateFlow(10)
    val timeLeft: StateFlow<Int> = _timeLeft

    init {
        Log.d("Route", "Splash--ViewModel init")
        startCountDown()
    }

    private fun startCountDown() {
       timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timeLeft.value = millisUntilFinished.toInt() / 1000
            }

            override fun onFinish() {
                Log.d("Route", "onFinish")
                _timeLeft.value = 0
            }
        }.start()
    }

    fun stopCountDown() {
        timer?.cancel()
    }


    override fun onCleared() {
        super.onCleared()
        stopCountDown()
        Log.d("Route", "Splash--ViewModel onCleared")
    }
}