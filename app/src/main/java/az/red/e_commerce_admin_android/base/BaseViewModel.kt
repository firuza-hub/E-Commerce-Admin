package az.red.e_commerce_admin_android.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.utils.*
import az.red.e_commerce_admin_android.utils.enums.AuthenticationStatus
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
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
                        UIEvent.Message(
                            "Unauthorized"
                        )
                    )
                    triggerEvent(
                        UIEvent.Navigate(
                            Graph.AUTH
                        )
                    )
                }
            }
        }
    }


    fun <T : Any> NetworkResult<T>.handleResult(
        onSuccess: (data: T) -> Unit,
        isLoading: MutableStateFlow<Boolean>,
        errorMessage: MutableStateFlow<String?>,
        tag: String? = null
    ) {
        when (this) {
            is NetworkResult.Empty -> {
                isLoading.value = false
                Log.i(tag, "Empty")
                triggerEvent(UIEvent.Error("no data"))
            }
            is NetworkResult.Error -> {
                isLoading.value = false
                errorMessage.value = this.message
                Log.i(tag, "Error: ${this.message}")
                triggerEvent(UIEvent.Message("no data"))
            }
            is NetworkResult.Exception -> {
                isLoading.value = false
                errorMessage.value = this.message
                Log.i(tag, "Exception: ${this.message}")
                triggerEvent(UIEvent.Error("no data"))
            }
            is NetworkResult.Loading -> {
                isLoading.value = true
                Log.i(tag, "Loading")
            }
            is NetworkResult.Success -> {
                isLoading.value = false
                if (this.data != null) {
                    onSuccess(this.data)
                    Log.i(tag, "Success")
                } else {
                    triggerEvent(UIEvent.Error("no data"))
                    Log.i(tag, "Success -> no data")
                }
            }
        }
    }

}