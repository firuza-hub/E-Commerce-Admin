package az.red.e_commerce_admin_android.ui.screens.register

import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.common.state.ErrorState


val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.auth_error_msg_empty_password
)

val emailEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.auth_error_msg_empty_email
)
val firstNameEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.auth_error_msg_empty_first_name
)
val lastNameEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.auth_error_msg_empty_last_name
)

val loginNameEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.auth_error_msg_empty_login
)

val fullNameEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.user_error_msg_empty_full_name
)

val nickNameEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.user_error_msg_empty_nick_name
)

val phoneNumberEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.auth_error_msg_empty_phone
)

val genderEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.auth_error_msg_empty_gender
)