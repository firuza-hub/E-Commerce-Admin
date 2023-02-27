package az.red.e_commerce_admin_android.utils

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

object AuthTracker {
    private val authenticated = Channel<Boolean>()
    val authenticationTrackerFlow = authenticated.receiveAsFlow()
    suspend fun triggerLogout() = authenticated.send(false)
}
