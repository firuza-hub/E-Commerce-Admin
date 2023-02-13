package az.red.e_commerce_admin_android.ui.common.state

import androidx.annotation.StringRes
import az.red.e_commerce_admin_android.R


data class ErrorState(
    val hasError: Boolean = false,
    @StringRes val errorMessageStringResource: Int = R.string.empty_string
)