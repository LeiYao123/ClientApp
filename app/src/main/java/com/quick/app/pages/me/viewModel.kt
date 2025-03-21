package com.quick.app.pages.me

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.quick.app.data.PreferencesManager
import com.quick.app.models.Session

class MeViewModel : ViewModel() {
    var session = mutableStateOf<Session?>(getPrefsSession())
    var num = mutableIntStateOf(0)

    init {
        Log.d("MeViewModel", "me 我执行了")
    }

    fun getPrefsSession(): Session? {
        return PreferencesManager.getObject<Session>("session")
    }

    fun logout() {
        session.value = null
        PreferencesManager.remove("session")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MeViewModel", "me 我被销毁了")
    }
}