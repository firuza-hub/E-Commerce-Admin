package az.red.e_commerce_admin_android.ui.screens.register

import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.common.state.ErrorState


val emptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_password
)