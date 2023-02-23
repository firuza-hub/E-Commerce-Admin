package az.red.e_commerce_admin_android.ui.screens.register

import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.auth.AuthRepository
import az.red.e_commerce_admin_android.data.remote.auth.dto.request.RegisterRequest
import az.red.e_commerce_admin_android.data.remote.auth.dto.response.RegisterResponse
import az.red.e_commerce_admin_android.ui.screens.login.LoginState
import az.red.e_commerce_admin_android.utils.NetworkResult
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authRepo: AuthRepository
) : BaseViewModel() {

    private var _registerResponse: MutableStateFlow<NetworkResult<RegisterResponse>> =
        MutableStateFlow(NetworkResult.Empty())
    var registerResponse: Flow<NetworkResult<RegisterResponse>> = _registerResponse.asStateFlow()

    val registerState = MutableStateFlow(RegisterState.NULL)

    private fun register(registerData: RegisterRequest) = viewModelScope.launch {
        authRepo.register(registerData).collect {
            _registerResponse.emit(it)
        }
    }

}