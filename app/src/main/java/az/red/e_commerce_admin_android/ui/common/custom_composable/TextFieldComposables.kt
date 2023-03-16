package az.red.e_commerce_admin_android.ui.common.custom_composable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.common.bottomTop
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.noRippleClickable
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import az.red.e_commerce_admin_android.utils.PhoneVisualTransformation
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
    CustomTextField(modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = label, fontSize = 18.sp, color = CustomTheme.colors.hintText
            )
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
                    tint = if (value.isNotBlank()) CustomTheme.colors.accent else CustomTheme.colors.hintText
                )
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password, imeAction = imeAction
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
        })
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
    CustomTextField(modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = label, fontSize = 18.sp, color = CustomTheme.colors.hintText
            )
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
    modifier: Modifier = Modifier, text: String
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
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions? = null,
    keyboardActions: KeyboardActions? = null,
    placeholder: @Composable (() -> Unit)? = null,
    hasError: Boolean = false,
    errorText: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false
) {
    var errorBorderWidth by remember { mutableStateOf(1.dp) }
    var errorVisibilityState by remember { mutableStateOf(false) }
    errorVisibilityState = hasError
    BasicTextField(
        modifier = modifier
            .background(CustomTheme.colors.cardBackground)
            .onFocusChanged {
                errorBorderWidth = if (it.hasFocus) 2.dp else 1.dp
            }
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = CustomTheme.colors.cardBorder
            ),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        readOnly = readOnly,
        textStyle = CustomTheme.typography.inputText(CustomTheme.colors.text),
        cursorBrush = SolidColor(CustomTheme.colors.cardBorder),
        decorationBox = { innerTextField ->
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier.height(44.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    if (leadingIcon != null) 
                        Column(modifier = Modifier.padding(8.dp)) { leadingIcon() }
                    else
                        Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        Modifier.weight(1f)
                    ) {
                        if (value.isEmpty() && placeholder != null) placeholder()

                        innerTextField()
                    }
                    if (trailingIcon != null) Column(modifier = Modifier.padding(12.dp)) { trailingIcon()}
                }

                AnimatedVisibility(
                    visible = errorVisibilityState, enter = fadeIn(), exit = fadeOut()
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

@Composable
fun StringTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorText: String = "",
    keyboardType: KeyboardType,
    imeAction: ImeAction = ImeAction.Next,
) {
    CustomTextField(modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = label, fontSize = 18.sp, color = CustomTheme.colors.hintText
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction,
        ),
        hasError = isError,
        errorText = {
            if (isError) {
                ErrorTextInputField(text = errorText)
            }
        })
}

@Composable
fun StringTextFieldPhoneNumber(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorText: String = "",
    leadingIcon: Int,
    keyboardType: KeyboardType,
    imeAction: ImeAction = ImeAction.Next,
    mask: String = "+380 (xx) xxx xx xx",
    maskChar: Char = 'x',
) {
    CustomTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = label, fontSize = 18.sp, color = CustomTheme.colors.hintText
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
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
                painter = painterResource(id = leadingIcon),
                tint = Color.Unspecified,
                contentDescription = "UKR Flag Icon"
            )
        },
        visualTransformation = PhoneVisualTransformation(mask, maskChar)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StringTextFieldWithTrailingIcon(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit,
    trailingIcon: Int,
    imeAction: ImeAction = ImeAction.Next
) {
    var pickedDate = if (value.isNotBlank()) LocalDate.parse(
        value,
        DateTimeFormatter.ISO_LOCAL_DATE
    ) else LocalDate.now()

    val dateDialogState = rememberMaterialDialogState()

    CustomTextField(
        modifier = modifier
            .height(47.dp)
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        trailingIcon = {
            Icon(painter = painterResource(id = trailingIcon),
                tint = CustomTheme.colors.inputIconHint,
                contentDescription = "Date Icon",
                modifier = Modifier.noRippleClickable {
                    dateDialogState.show()
                })
        },
//        shape = RoundedCornerShape(8.dp),
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            focusedBorderColor = CustomTheme.colors.inputIconHint,
//            unfocusedBorderColor = CustomTheme.colors.btnTextDisabled
//        ),
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        readOnly = true
    )

    MaterialDialog(dialogState = dateDialogState, buttons = {
        positiveButton(text = stringResource(id = R.string.ok))
        negativeButton(text = stringResource(id = R.string.cancel))
    }) {
        datepicker(
            initialDate = pickedDate,
            title = stringResource(id = R.string.pick_a_date),
        ) {
            pickedDate = it
            onValueChange(pickedDate.toString())
        }
    }

}

