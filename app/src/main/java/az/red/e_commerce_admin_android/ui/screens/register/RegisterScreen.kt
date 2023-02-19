package az.red.e_commerce_admin_android.ui.screens.register

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import az.red.e_commerce_admin_android.ui.navigation.auth.AuthScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = koinViewModel<RegisterViewModel>()
) {
    Column {
        Text(text = "REGISTER")
        Button(onClick = { navController.navigate(AuthScreen.LoginAuthScreen.route) }) {
            Text(text = "LOGIN")
        }
    }
}