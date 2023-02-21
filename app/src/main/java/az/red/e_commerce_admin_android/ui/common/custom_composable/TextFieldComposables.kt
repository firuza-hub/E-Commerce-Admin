package az.red.e_commerce_admin_android.ui.common.custom_composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.themes.CustomTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorText: String = "",
    imeAction: ImeAction = ImeAction.Done
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    CustomTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = label, fontSize = 18.sp)
        },
        trailingIcon = {
            IconButton(onClick = {
                isPasswordVisible = !isPasswordVisible
            }) {

                val visibleIconAndText = Pair(
                    first = Icons.Outlined.Visibility,
                    second = stringResource(id = R.string.icon_password_visible)
                )

                val hiddenIconAndText = Pair(
                    first = Icons.Outlined.VisibilityOff,
                    second = stringResource(id = R.string.icon_password_hidden)
                )

                val passwordVisibilityIconAndText =
                    if (isPasswordVisible) visibleIconAndText else hiddenIconAndText

                // Render Icon
                Icon(
                    imageVector = passwordVisibilityIconAndText.first,
                    contentDescription = passwordVisibilityIconAndText.second,
                    tint = if(value.isNotBlank()) CustomTheme.colors.accent else CustomTheme.colors.hintText
                )
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
        hasError = isError,
        errorText = {
            if (isError) {
                ErrorTextInputField(text = errorText)
            }
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_password),
                tint = CustomTheme.colors.inputIconHint,
                contentDescription = "Email Icon"
            )
        }
    )
}

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorText: String = "",
    imeAction: ImeAction = ImeAction.Next
) {
    CustomTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = label, fontSize = 18.sp)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = imeAction,
        ),
        hasError = isError,
        errorText = {
            if (isError) {
                ErrorTextInputField(text = errorText)
            }
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_email),
                tint = CustomTheme.colors.inputIconHint,
                contentDescription = "Email Icon"
            )
        })

}


@Composable
fun ErrorTextInputField(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 4.dp),
        text = text,
        style = CustomTheme.typography.body2,
        color = CustomTheme.colors.error
    )
}

@Composable
private fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions? = null,
    keyboardActions: KeyboardActions? = null,
    placeholder: @Composable() (() -> Unit)? = null,
    hasError: Boolean,
    errorText: @Composable() (() -> Unit)? = null,
) {
    var errorBorderWidth by remember { mutableStateOf(1.dp) }
    var errorVisibilityState by remember { mutableStateOf(false) }
    errorVisibilityState = hasError
    BasicTextField(
        modifier = modifier
            .onFocusChanged {
                errorBorderWidth =
                    if (it.hasFocus) 2.dp else 1.dp
            }
            .border(
                width = 1.dp, shape = RoundedCornerShape(8.dp), color = colorResource(
                    id = R.color.input_card_border_light
                )
            ),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        cursorBrush = SolidColor(CustomTheme.colors.cardBorder),
        decorationBox = { innerTextField ->
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier.height(44.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (leadingIcon != null) Column(modifier = Modifier.padding(8.dp)) { leadingIcon() }
                    Box(
                        Modifier.weight(1f)
                    ) {
                        if (value.isEmpty() && placeholder != null)
                            placeholder()

                        innerTextField()
                    }
                    if (trailingIcon != null) trailingIcon()
                }

                AnimatedVisibility(
                    visible = errorVisibilityState,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .bottomTop(errorBorderWidth, CustomTheme.colors.error)
                    ) {
                        errorText!!()
                    }
                }
            }
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions ?: KeyboardOptions.Default,
        keyboardActions = keyboardActions ?: KeyboardActions.Default
    )
}

fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx / 2

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = width, y = height),
                strokeWidth = strokeWidthPx
            )
        }
    }
)

fun Modifier.bottomTop(strokeWidth: Dp, color: Color) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width

            drawLine(
                color = color,
                start = Offset(x = 0f, y = strokeWidthPx),
                end = Offset(x = width, y = strokeWidthPx),
                strokeWidth = strokeWidthPx,
                pathEffect = null)
        }
    }
)