package az.red.e_commerce_admin_android.ui.screens.login

sealed class LoginUIEvent {
    data class EmailChanged(val inputValue: String) : LoginUIEvent()
    data class PasswordChanged(val inputValue: String) : LoginUIEvent()
    object Submit : LoginUIEvent()}