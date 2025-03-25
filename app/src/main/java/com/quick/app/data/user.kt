package com.quick.app.data

import com.quick.app.models.Session

object UserUtil {
    fun getPrefsSession(): Session? {
        return PreferencesManager.getObject<Session>("session")
    }

    fun removeSession() {
        PreferencesManager.remove("session")
    }

    fun saveSession(data: Session?) {
        PreferencesManager.putObject("session", data)
    }
}