package az.red.e_commerce_admin_android.ui.themes

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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

    val sansSerif20: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),

    val body3: TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),


    val nunitoNormal12: TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    val nunitoNormal14: TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    val nunitoNormal16: TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    val nunitoThin16: TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Thin,
        fontSize = 16.sp
    ),

    val nunitoSemiBold16: TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),

    val nunitoNormal18: TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),

    val nunitoNormal14StrikeThrough: TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        textDecoration =  TextDecoration.LineThrough
    ),
    val nunitoBold14: TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),

    val nunitoExtraBold14: TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp
    ),

    val montSerratSemiBold: TextStyle = TextStyle(
        fontFamily = montSerratFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),

    val nunitoBold18: TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),

    val nunitoBold24: TextStyle = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
){


        fun inputText(color: Color): TextStyle = TextStyle(
            fontFamily = nunitoFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = color
        )

}

val LocalTypography = staticCompositionLocalOf {
    CustomTypography()
}