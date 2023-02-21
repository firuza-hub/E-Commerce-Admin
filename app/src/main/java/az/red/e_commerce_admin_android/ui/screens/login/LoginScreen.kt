package az.red.e_commerce_admin_android.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.common.custom_composable.EmailTextField
import az.red.e_commerce_admin_android.ui.common.custom_composable.PasswordTextField
import az.red.e_commerce_admin_android.ui.navigation.auth.AuthScreen
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    val state by loginViewModel.loginState.collectAsState()
    val isLoggedInState by loginViewModel.isLoggedIn.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        launch {
            loginViewModel.uiEventFlow.collect { event ->
                when (event) {
                    is UIEvent.Error -> {
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    }
                    is UIEvent.Message -> {
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    }
                    is UIEvent.Navigate -> {
                        navController.navigate(route = event.route)
                    }
                }
            }
        }
    }

    if (!isLoggedInState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = stringResource(id = R.string.sing_in_to_account),
                style = CustomTheme.typography.h1,
                color = CustomTheme.colors.text
            )
            Spacer(modifier = Modifier.height(45.dp))

            InputSection(state, onPasswordChange = {
                loginViewModel.onUiEvent(
                    loginUiEvent = LoginUIEvent.PasswordChanged(
                        it
                    )
                )
            }, onEmailChange = {
                loginViewModel.onUiEvent(
                    loginUiEvent = LoginUIEvent.EmailChanged(
                        it
                    )
                )
            })

            Spacer(modifier = Modifier.height(45.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = state.rememberMe,
                    onCheckedChange = {
                        loginViewModel.onUiEvent(
                            loginUiEvent = LoginUIEvent.RememberMeChanged(
                                it
                            )
                        )
                    },
                    colors = CheckBoxColors()
                )
                Text(text = stringResource(id = R.string.remember_me))
            }

            Spacer(modifier = Modifier.height(45.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .clip(RoundedCornerShape(28.dp)),
                colors = AuthButtonColors(),
                onClick = { loginViewModel.onUiEvent(LoginUIEvent.Submit) },
                enabled = state.btnEnabled
            ) {
                Text(
                    text = stringResource(id = R.string.sign_in),
                    style = CustomTheme.typography.nunitoBold18
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.forgot_the_password),
                style = CustomTheme.typography.body3,
                color = CustomTheme.colors.text
            )
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Divider(
                    color = CustomTheme.colors.inputIconHint,
                    thickness = 1.dp,
                    modifier = Modifier.width(80.dp)
                )
                Text(
                    text = stringResource(id = R.string.or_continue_with),
                    style = CustomTheme.typography.body3,
                    color = CustomTheme.colors.text
                )
                Divider(
                    color = CustomTheme.colors.inputIconHint,
                    thickness = 1.dp,
                    modifier = Modifier.width(80.dp)
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AuthIcon(painterResource(id = R.drawable.ic_facebook))
                AuthIcon(painterResource(id = R.drawable.ic_google))
                AuthIcon(painterResource(id = R.drawable.ic_apple))
            }
            Spacer(modifier = Modifier.height(45.dp))
            Row {
                Text(
                    text = stringResource(id = R.string.don_t_have_account),
                    color = CustomTheme.colors.text,
                    style = CustomTheme.typography.body3
                )
                Text(
                    text = stringResource(id = R.string.sign_up),
                    modifier = Modifier
                        .clickable {
                            navController.navigate(AuthScreen.RegisterAuthScreen.route)
                        }
                        .padding(start = 10.dp),
                    color = CustomTheme.colors.accent,
                    style = CustomTheme.typography.body3
                )
            }
        }
    }
}

@Composable
fun CheckBoxColors(): CheckboxColors {
    return CheckboxDefaults.colors(
        checkedColor = CustomTheme.colors.accent,
        uncheckedColor = CustomTheme.colors.accent,
    )
}

@Composable
fun AuthButtonColors(): ButtonColors {
    return ButtonDefaults.buttonColors(
        disabledBackgroundColor = CustomTheme.colors.btnBackgroundInactive,
        backgroundColor = CustomTheme.colors.btnBackgroundActive,
        contentColor = CustomTheme.colors.btnText,
        disabledContentColor = CustomTheme.colors.btnTextDisabled
    )
}

@Composable
fun InputSection(
    state: LoginState,
    onEmailChange: (newValue: String) -> Unit,
    onPasswordChange: (newValue: String) -> Unit
) {

    EmailTextField(
        modifier = Modifier.fillMaxWidth(),
        state.email, { onEmailChange(it) },
        stringResource(id = R.string.email),
        state.errorState.emailOrMobileErrorState.hasError,
        state.errorState.emailOrMobileErrorState.errorMessageStringResource?.let{stringResource(id = it)}
            ?: state.errorState.emailOrMobileErrorState.errorMessage ,
    )

    Spacer(modifier = Modifier.height(16.dp))

    PasswordTextField(
        modifier = Modifier.fillMaxWidth(),
        state.password, { onPasswordChange(it) },
        stringResource(id = R.string.password),
        state.errorState.passwordErrorState.hasError,
        state.errorState.passwordErrorState.errorMessageStringResource?.let{stringResource(id = it)}
            ?: state.errorState.passwordErrorState.errorMessage,
        ImeAction.Done
    )

}

@Composable
fun AuthIcon(iconPainter: Painter) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp, color = CustomTheme.colors.cardBorder,
                RoundedCornerShape(8.dp)
            )
            .size(44.dp), contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = iconPainter,
            contentDescription = stringResource(R.string.content_description_facebook),
            modifier = Modifier.height(16.dp),
            tint = CustomTheme.colors.text
        )
    }
}


