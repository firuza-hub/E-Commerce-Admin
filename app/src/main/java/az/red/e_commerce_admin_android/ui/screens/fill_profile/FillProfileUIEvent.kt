package az.red.e_commerce_admin_android.ui.screens.fill_profile

sealed class FillProfileUIEvent {
    data class FullNameChanged(val inputValue: String) : FillProfileUIEvent()
    data class NickNameChanged(val inputValue: String) : FillProfileUIEvent()
    data class EmailChanged(val inputValue: String) : FillProfileUIEvent()
    data class DateOfBirthChanged(val inputValue: String) : FillProfileUIEvent()
    data class PhoneNumberChanged(val inputValue: String) : FillProfileUIEvent()
    data class GenderChanged(val inputValue: String) : FillProfileUIEvent()
    data class AvatarChanged(val inputValue: String) : FillProfileUIEvent()
    object Continue : FillProfileUIEvent()
}