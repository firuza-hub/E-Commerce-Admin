package az.red.e_commerce_admin_android.ui.screens.login

import androidx.annotation.StringRes
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.data.remote.auth.dto.request.LoginRequest

data class LoginState(
    val email: String,
    val password: String,
    val errorState: LoginErrorState = LoginErrorState(),
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

data class ErrorState(
    val hasError: Boolean = false,
    @StringRes val errorMessageStringResource: Int = R.string.empty_string
)