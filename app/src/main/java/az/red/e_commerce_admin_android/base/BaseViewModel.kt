package az.red.e_commerce_admin_android.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.data.remote.auth.SessionManager
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

abstract class BaseViewModel : ViewModel() {
    protected val sessionManager:SessionManager by inject(SessionManager::class.java)
    private val uiEventChannel = Channel<UIEvent>()
    val uiEventFlow = uiEventChannel.receiveAsFlow()

    fun triggerEvent(event: UIEvent) = viewModelScope.launch {
        uiEventChannel.send(event)
    }

    //TODO: Call this method on the home screen
    protected fun authorizationCheck() {
        sessionManager.fetchAuthToken().let {
            if (it.isNullOrEmpty()) {
                Log.i("BASE_VIEW_MODEL", "Token is empty. Redirect to login")
                triggerEvent(UIEvent.Navigate(R.id.loginFragment))
            }
        }
    }
}