package az.red.e_commerce_admin_android.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneVisualTransformation(private val mask: String, private val maskChar: Char) :
    VisualTransformation {

    private val maxLength = mask.count { it == maskChar }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.length > maxLength) text.take(maxLength) else text

        val annotatedString = buildAnnotatedString {
            if (trimmed.isEmpty()) return@buildAnnotatedString

            var maskIndex = 0
            var textIndex = 0
            while (textIndex < trimmed.length && maskIndex < mask.length) {
                if (mask[maskIndex] != maskChar) {
                    val nextDigitIndex = mask.indexOf(maskChar, maskIndex)
                    append(mask.substring(maskIndex, nextDigitIndex))
                    maskIndex = nextDigitIndex
                }
                append(trimmed[textIndex++])
                maskIndex++
            }
        }

        return TransformedText(annotatedString, PhoneOffsetMapper(mask, maskChar))
    }

    private class PhoneOffsetMapper(val mask: String, val maskChar: Char) : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val safeOffset =   if(offset > mask.count { it == maskChar }) mask.count { it == maskChar } else offset
            var noneDigitCount = 0
            var i = 0
            while (i < safeOffset + noneDigitCount) {
                val nextMaskValue = mask[i++]
                if (nextMaskValue != maskChar) noneDigitCount++
            }
            return safeOffset + noneDigitCount
        }

        override fun transformedToOriginal(offset: Int): Int =
            offset - mask.take(offset).count { it != maskChar }
    }
}