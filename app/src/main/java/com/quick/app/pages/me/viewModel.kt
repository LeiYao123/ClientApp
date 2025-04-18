package com.quick.app.pages.me

//import com.quick.app.components.drawer.DrawerCenter
import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quick.app.components.toast.Toast
import com.quick.app.components.toast.ToastStatus
import com.quick.app.data.PreferencesManager
import com.quick.app.models.Session
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MeViewModel : ViewModel() {
    var session = mutableStateOf<Session?>(getPrefsSession())
    var num = mutableIntStateOf(0)

    init {
        Log.d("MeViewModel", "me 我执行了")

        viewModelScope.launch {
            delay(2000)
            Toast.show("test toast show in viewModel", status = ToastStatus.SUCCESS)

//            DrawerCenter.open(DrawerEntry(id = "test", content = {
//                TestDrawer()
//            }))
        }
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


