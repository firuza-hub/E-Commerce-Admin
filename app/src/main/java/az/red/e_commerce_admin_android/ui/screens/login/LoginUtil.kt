package az.red.e_commerce_admin_android.ui.screens.login

import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.common.state.ErrorState

val emailOrMobileEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_email_mobile
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_password
)