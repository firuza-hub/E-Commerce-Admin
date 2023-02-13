package az.red.e_commerce_admin_android.ui.register

import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.auth.AuthRepository
import az.red.e_commerce_admin_android.data.remote.auth.dto.request.RegisterRequest
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authRepo: AuthRepository
) : BaseViewModel() {
    fun register(registerData: RegisterRequest) {
        viewModelScope.launch {
            authRepo.register(registerData)
        }
    }


}