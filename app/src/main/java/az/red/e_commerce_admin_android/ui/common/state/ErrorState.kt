package az.red.e_commerce_admin_android.ui.common.state

import androidx.annotation.StringRes


data class ErrorState(
    val hasError: Boolean = false,
    val errorMessage:String  = "",
    @StringRes val errorMessageStringResource: Int? = null
)