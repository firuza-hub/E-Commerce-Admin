package az.red.e_commerce_admin_android.ui.screens.register


import az.red.e_commerce_admin_android.data.remote.auth.dto.request.RegisterRequest
import az.red.e_commerce_admin_android.ui.common.state.ErrorState

data class RegisterState(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val loginName: String,
    val telephone: String,
    val gender: String,
    val errorState: RegisterErrorState = RegisterErrorState(),
    val isRegisterSuccessful: Boolean = false
) {
    companion object {
        val NULL = RegisterState("", "", "", "", "", "", "")
    }

    fun toRegisterRequest() = RegisterRequest(
        email = email,
        firstName = firstName,
        lastName = lastName,
        login = loginName,
        password = password,
        telephone = telephone,
        gender = gender,
        avatarUrl = "",
        isAdmin = true
    )
}

data class RegisterErrorState(
    val emailOrMobileErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState(),
    val firstNameErrorState: ErrorState = ErrorState(),
    val lastNameErrorState: ErrorState = ErrorState(),
    val loginNameErrorState: ErrorState = ErrorState(),
    val telephoneErrorState: ErrorState = ErrorState(),
    val genderErrorState: ErrorState = ErrorState(),
)
