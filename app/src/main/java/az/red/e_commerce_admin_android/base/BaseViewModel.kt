package az.red.e_commerce_admin_android.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.utils.*
import az.red.e_commerce_admin_android.utils.enums.AuthenticationStatus
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

    init {
        viewModelScope.launch {
            EventBus.subscribe<AuthenticationStatus> { authed ->

                if (authed == AuthenticationStatus.UNAUTHENTICATED) {
                    println("LOGOUT TRIGGERED")
                    triggerEvent(
                        UIEvent.Navigate(
                            Graph.AUTH
                        )
                    )
                }
            }
        }
    }

}