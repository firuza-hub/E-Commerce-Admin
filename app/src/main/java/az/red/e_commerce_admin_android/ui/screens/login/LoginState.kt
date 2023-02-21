package az.red.e_commerce_admin_android.ui.screens.login

import az.red.e_commerce_admin_android.data.remote.auth.dto.request.LoginRequest
import az.red.e_commerce_admin_android.ui.common.state.ErrorState

data class LoginState(
    val email: String,
    val password: String,
    val errorState: LoginErrorState = LoginErrorState(),
    val rememberMe: Boolean = false,
    val btnEnabled: Boolean = false,
    val isLoginSuccessful: Boolean = false
) {

    companion object {
        val NULL = LoginState("", "")
    }

    fun toLoginRequest() = LoginRequest(email, password)
}

data class LoginErrorState(
    val emailOrMobileErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState()
)
