package az.red.e_commerce_admin_android.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import az.red.e_commerce_admin_android.ui.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = koinViewModel<LoginViewModel>()
) {
    Column {
        Text(text = "LOGIN")
        Button(onClick = { navController.navigate(Screen.RegisterScreen.route) }) {
            Text(text = "REGISTER")
        }
    }
}