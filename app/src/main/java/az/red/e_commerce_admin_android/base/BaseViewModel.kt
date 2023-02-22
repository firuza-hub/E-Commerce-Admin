package az.red.e_commerce_admin_android.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.utils.SessionManager
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

abstract class BaseViewModel : ViewModel() {
    protected val sessionManager: SessionManager by inject(SessionManager::class.java)
    private val uiEventChannel = Channel<UIEvent>()
    val uiEventFlow = uiEventChannel.receiveAsFlow()

    fun triggerEvent(event: UIEvent) = viewModelScope.launch {
        uiEventChannel.send(event)
    }

}