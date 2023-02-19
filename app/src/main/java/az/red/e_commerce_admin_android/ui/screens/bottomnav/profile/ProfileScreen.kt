package az.red.e_commerce_admin_android.ui.screens.bottomnav.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import az.red.e_commerce_admin_android.data.remote.auth.SessionManager
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import org.koin.androidx.compose.inject

@Composable
fun ProfileScreen(navigateToRoot: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        val sessionManager by inject<SessionManager>()
        Text(text = "Profile", fontSize = 18.sp)
        Button(onClick = {
            sessionManager.removeAuthToken()
            navigateToRoot()
        }) {
            Text(text = "LOGOUT")
        }
    }
}