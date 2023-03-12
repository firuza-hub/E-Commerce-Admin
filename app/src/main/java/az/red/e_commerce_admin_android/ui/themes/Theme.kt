package az.red.e_commerce_admin_android.ui.themes


import CustomSpaces
import LocalSpaces
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

fun lightColors() = CustomColors(
    primary = Color(0xFFE67E22),
    text = TextLight,
    background = Color(0xFFFFFFFF),
    success = Color(0xFF2ECC71),
    error = Color(0xFFE74C3C),
    isLight = true,
    accent = AccentCarrot,
    cardBackground = InputCardBackgroundLight,
    hintText = InputHintColorLight,
    cardBorder = InputCardBorderLight,
    inputIcon = TextLight,
    inputIconHint = InputHintColorLight,
    btnBackgroundActive = TextLight,
    btnBackgroundInactive = InputHintColorLight,
    btnText = BtnTextLight,
    btnTextDisabled = InputCardBorderLight,
    darkBtnBackground = InputCardBackgroundDark,
    textReverse = TextDark,
    btnTextAlwaysLight = BtnTextLight,
    imageCardBackground = ImageCardBackgroundLight,
    orderStatusCompletedButton = OrderStatusCompletedButton,
    orderStatusCancelledButton = OrderStatusCancelledButton
)

fun darkColors() = CustomColors(
    primary = Color(0xFFDF6900),
    text = TextDark,
    background = BackgroundDark,
    success = Color(0xFF44BD32),
    error = Color(0xFFC23616),
    isLight = false,
    accent = AccentCarrot,
    cardBackground = InputCardBackgroundDark,
    hintText = InputHintColorDark,
    cardBorder = InputCardBorderDark,
    inputIcon = TextDark,
    inputIconHint = InputHintColorDark,
    btnBackgroundActive = TextDark,
    btnBackgroundInactive = InputHintColorLight,
    btnText = BtnTextDark,
    btnTextDisabled = InputCardBorderLight,
    darkBtnBackground = InputHintColorDark,
    textReverse = TextLight,
    btnTextAlwaysLight = BtnTextLight,
    imageCardBackground = ImageCardBackgroundDark,
    orderStatusCompletedButton = OrderStatusCompletedButton,
    orderStatusCancelledButton = OrderStatusCancelledButton
)

@Composable
fun AppTheme(
    spaces: CustomSpaces = CustomTheme.spaces,
    typography: CustomTypography = CustomTheme.typography,
    colors: CustomColors = CustomTheme.colors,
    darkColors: CustomColors? = darkColors(),
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val currentColor =  if (darkColors != null && isDarkTheme) darkColors else colors
    val rememberedColors = remember { currentColor.copy() }.apply { updateColorsFrom(currentColor) }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalSpaces provides spaces,
        LocalTypography provides typography,
    ) {
        ProvideTextStyle(typography.body1.copy(color = CustomTheme.colors.text), content = content)
    }
}



