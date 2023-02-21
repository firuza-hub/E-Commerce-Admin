package az.red.e_commerce_admin_android.ui.common.state

import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import az.red.e_commerce_admin_android.R


data class ErrorState(
    val hasError: Boolean = false,
    val errorMessage:String  = "",
    @StringRes val errorMessageStringResource: Int? = null
)