package az.red.e_commerce_admin_android.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.common.custom_composable.EmailTextField
import az.red.e_commerce_admin_android.ui.common.custom_composable.PasswordTextField
import az.red.e_commerce_admin_android.ui.navigation.auth.AuthScreens
import az.red.e_commerce_admin_android.ui.themes.barlowFamily
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

    if (isLoggedInState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.sing_in_to_account), fontSize = 32.sp,
                fontFamily = barlowFamily,
                fontWeight = FontWeight.Bold
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
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(42.dp),
                onClick = { loginViewModel.onUiEvent(LoginUIEvent.Submit) }) {
                Text(text = stringResource(id = R.string.sign_in), fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(45.dp))
            Row {
                Text(text = stringResource(id = R.string.don_t_have_account))
                Text(
                    text = stringResource(id = R.string.sign_up),
                    modifier = Modifier.clickable {
                        navController.navigate(AuthScreens.RegisterAuthScreens.route)
                    },
                    color = Color.Red
                )
            }
        }
    }
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
        stringResource(id = state.errorState.emailOrMobileErrorState.errorMessageStringResource)
    )

    Spacer(modifier = Modifier.height(16.dp))

    PasswordTextField(
        modifier = Modifier.fillMaxWidth(),
        state.password, { onPasswordChange(it) },
        stringResource(id = R.string.password),
        state.errorState.passwordErrorState.hasError,
        stringResource(id = state.errorState.passwordErrorState.errorMessageStringResource),
        ImeAction.Done
    )

}


