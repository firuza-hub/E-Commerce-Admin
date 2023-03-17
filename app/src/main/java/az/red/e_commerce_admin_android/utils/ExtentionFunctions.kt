package az.red.e_commerce_admin_android.utils

import java.util.*

fun String.capitalizeCustom():String {
    return this.replaceFirstChar { fl ->
        if (fl.isLowerCase()) fl.titlecase(
            Locale.getDefault()
        ) else fl.toString()
    }
}