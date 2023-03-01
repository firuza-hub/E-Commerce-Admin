package az.red.e_commerce_admin_android.ui.themes

import android.graphics.Color.parseColor
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val TextLight = Color(0xFF000000)
val BtnTextLight = Color(0xFFFFFFFF)
val BtnTextDark = Color(0xFF000000)
val TextDark = Color(0xFFFFFFFF)
val AccentCarrot = Color(parseColor("#FF725E"))
val ButtonTextWhite = Color(parseColor("#F2F2F2"))
val InputCardBorderLight = Color(parseColor("#CFCFCF"))
val InputCardBorderDark = Color(parseColor("#233447"))
val InputCardBackgroundDark = Color(parseColor("#16212D"))
val InputCardBackgroundLight = Color(parseColor("#FFFFFFFF"))
val InputHintColorLight = Color(parseColor("#B7B7B7"))
val InputHintColorDark = Color(parseColor("#233447"))
val BackgroundDark = Color(parseColor("#131C26"))
val ImageCardBackgroundLight = Color(parseColor("#1A1A262C"))
val ImageCardBackgroundDark = Color(parseColor("#233447"))

class CustomColors(
    primary: Color,
    text: Color,
    hintText: Color,
    background: Color,
    cardBackground: Color,
    cardBorder: Color,
    inputIcon: Color,
    inputIconHint: Color,
    btnBackgroundInactive: Color,
    btnBackgroundActive: Color,
    btnText: Color,
    btnTextDisabled: Color,
    accent: Color,
    success: Color,
    error: Color,
    isLight: Boolean,
    darkBtnBackground: Color,
    textReverse: Color,
    imageCardBackground: Color
) {
    var primary by mutableStateOf(primary)
        private set

    var text by mutableStateOf(text)
        private set

    var success by mutableStateOf(success)
        private set

    var error by mutableStateOf(error)
        private set

    var background by mutableStateOf(background)
        private set

    var isLight by mutableStateOf(isLight)
        private set

    var accent by mutableStateOf(accent)
        private set

    var hintText by mutableStateOf(hintText)
        private set

    var cardBackground by mutableStateOf(cardBackground)
        private set

    var cardBorder by mutableStateOf(cardBorder)
        private set
    var inputIcon by mutableStateOf(inputIcon)
        private set
    var inputIconHint by mutableStateOf(inputIconHint)
        private set
    var btnBackgroundInactive by mutableStateOf(btnBackgroundInactive)
        private set
    var btnBackgroundActive by mutableStateOf(btnBackgroundActive)
        private set
    var btnText by mutableStateOf(btnText)
        private set
    var btnTextDisabled by mutableStateOf(btnTextDisabled)
        private set
    var darkBtnBackground by mutableStateOf(darkBtnBackground)
        private set
    var textReverse by mutableStateOf(textReverse)
        private set
    var imageCardBackground by mutableStateOf(imageCardBackground)
        private set

    fun copy(
        primary: Color = this.primary,
        text: Color = this.text,
        background: Color = this.background,
        success: Color = this.success,
        error: Color = this.error,
        isLight: Boolean = this.isLight,
        accent: Color = this.accent,
        hintText: Color = this.hintText,
        cardBackground: Color = this.cardBackground,
        cardBorder: Color = this.cardBorder,
        inputIcon: Color = this.inputIcon,
        inputIconHint: Color = this.inputIconHint,
        btnBackgroundInactive: Color = this.btnBackgroundInactive,
        btnBackgroundActive: Color = this.btnBackgroundActive,
        btnText: Color = this.btnText,
        btnTextDisabled: Color = this.btnTextDisabled,
        darkBtnBackground: Color = this.darkBtnBackground,
        textReverse: Color = this.textReverse,
        imageCardBackground: Color = this.imageCardBackground
    ) = CustomColors(
        primary = primary,
        text = text,
        background = background,
        success = success,
        error = error,
        isLight = isLight,
        accent = accent,
        hintText = hintText,
        cardBackground = cardBackground,
        cardBorder = cardBorder,
        inputIcon = inputIcon,
        inputIconHint = inputIconHint,
        btnBackgroundInactive = btnBackgroundInactive,
        btnBackgroundActive = btnBackgroundActive,
        btnText = btnText,
        btnTextDisabled = btnTextDisabled,
        darkBtnBackground = darkBtnBackground,
        textReverse = textReverse,
        imageCardBackground = imageCardBackground
    )

    fun updateColorsFrom(other: CustomColors) {
        primary = other.primary
        text = other.text
        success = other.success
        background = other.background
        error = other.error
        isLight = other.isLight
        accent = other.accent
        hintText = other.hintText
        cardBackground = other.cardBackground
        cardBorder = other.cardBorder
        inputIcon = other.inputIcon
        inputIconHint = other.inputIconHint
        btnBackgroundInactive = other.btnBackgroundInactive
        btnBackgroundActive = other.btnBackgroundActive
        btnText = other.btnText
        btnTextDisabled = other.btnTextDisabled
        darkBtnBackground = other.darkBtnBackground
        textReverse = other.textReverse
        imageCardBackground = other.imageCardBackground
    }


}

val LocalColors = staticCompositionLocalOf { lightColors() }