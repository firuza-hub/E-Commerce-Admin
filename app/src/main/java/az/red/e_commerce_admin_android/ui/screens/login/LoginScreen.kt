package az.red.e_commerce_admin_android.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.navigation.Screen
import az.red.e_commerce_admin_android.ui.themes.barlowFamily
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    val state by loginViewModel.loginState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign in to account", fontSize = 32.sp,
            fontFamily = barlowFamily,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(45.dp))

        InputSection(state, onPasswordChange = {
            loginViewModel.onUiEvent(
                loginUiEvent = LoginUiEvent.PasswordChanged(
                    it
                )
            )
        }, onEmailChange = {
            loginViewModel.onUiEvent(
                loginUiEvent = LoginUiEvent.EmailChanged(
                    it
                )
            )
        })
        Spacer(modifier = Modifier.height(45.dp))
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = { loginViewModel.login() }) {
            Text(text = "Sign in")
        }

        Spacer(modifier = Modifier.height(45.dp))
        Row {
            Text(text = "Don't have an account? ")
            Text(
                text = "Sign up",
                modifier = Modifier.clickable {
                    navController.navigate(Screen.RegisterScreen.route)
                },
                color = Color.Red
            )
        }
    }
}
@Composable
fun InputSection(
    state: LoginState,
    onEmailChange: (newValue: String) -> Unit,
    onPasswordChange: (newValue: String) -> Unit
) {
    val password by rememberSaveable {
        mutableStateOf(state.password)
    }
    val email by rememberSaveable {
        mutableStateOf(state.email)
    }
    InputCard(label = "Email", state.email) { onEmailChange(it) }
    Spacer(modifier = Modifier.height(16.dp))
    InputCard(label = "Password", state.password) { onPasswordChange(it) }

}

@Composable
fun InputCard(label: String, value: String, onValueChanged: (newValue: String) -> Unit) {
    BasicTextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(8.dp), color = colorResource(
                    id = R.color.input_card_background_light
                )
            )
            .border(
                width = 1.dp, color = colorResource(
                    id = R.color.input_card_border_light
                ), shape = RoundedCornerShape(8.dp)
            )
            .padding(13.dp)
    )

}

