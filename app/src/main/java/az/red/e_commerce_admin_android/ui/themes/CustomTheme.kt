package az.red.e_commerce_admin_android.ui.themes

import CustomSpaces
import LocalSpaces
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable


object CustomTheme {
    val colors: CustomColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: CustomTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val spaces: CustomSpaces
        @Composable
        @ReadOnlyComposable
        get() = LocalSpaces.current
}