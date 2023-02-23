package az.red.e_commerce_admin_android.ui.screens.register

sealed class RegisterUIEvent {
    data class EmailChanged(val inputValue: String) : RegisterUIEvent()
    data class PasswordChanged(val inputValue: String) : RegisterUIEvent()
    data class FirstNameChanged(val inputValue: String) : RegisterUIEvent()
    data class LastNameChanged(val inputValue: String) : RegisterUIEvent()
    data class LoginNameChanged(val inputValue: String) : RegisterUIEvent()
    data class TelephoneChanged(val inputValue: String) : RegisterUIEvent()
    data class GenderChanged(val inputValue: String) : RegisterUIEvent()
    object Submit : RegisterUIEvent()
}