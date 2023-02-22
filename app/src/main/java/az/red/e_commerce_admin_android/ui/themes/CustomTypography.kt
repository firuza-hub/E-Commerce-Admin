package az.red.e_commerce_admin_android.ui.themes

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class CustomTypography(
    val h1: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    val body1: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    val body2: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    val nunitoNormal12 : TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    val nunitoNormal16 : TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    val nunitoNormal18 : TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    val nunitoBold : TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),

    val montSerratSemiBold : TextStyle = TextStyle(
        fontFamily = montSerratFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )
)

val LocalTypography = staticCompositionLocalOf {
    CustomTypography()
}