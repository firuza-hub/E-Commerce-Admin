package az.red.e_commerce_admin_android.ui.screens.fill_profile

import az.red.e_commerce_admin_android.ui.common.state.ErrorState

data class UserProfileState(
    val fullName: String,
    val nickName: String,
    val email: String,
    val dateOfBirth: String,
    val phoneNumber: String,
    val gender: String,
    var avatarUrl: String?,
    val errorState: FillProfileErrorState = FillProfileErrorState(),
    val btnEnabled: Boolean = false
) {
    companion object {
        val NULL = UserProfileState("", "", "", "", "", "", "")
    }
}

data class FillProfileErrorState(
    val fullNameErrorState: ErrorState = ErrorState(),
    val nickNameErrorState: ErrorState = ErrorState(),
    val emailErrorState: ErrorState = ErrorState(),
    val dateOfBirthErrorState: ErrorState = ErrorState(),
    val phoneNumberErrorState: ErrorState = ErrorState(),
    val genderErrorState: ErrorState = ErrorState()
)