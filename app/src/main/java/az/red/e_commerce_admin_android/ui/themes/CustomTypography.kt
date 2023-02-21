package az.red.e_commerce_admin_android.ui.themes

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class CustomTypography(
    val h1: TextStyle = TextStyle(
        fontFamily = barlowFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    val body1: TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    val body2: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    val body3: TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),


    val nunitoNormal12 : TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    val nunitoNormal18 : TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    val nunitoBold14 : TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),

    val nunitoBold18 : TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
)

val LocalTypography = staticCompositionLocalOf {
    CustomTypography()
}