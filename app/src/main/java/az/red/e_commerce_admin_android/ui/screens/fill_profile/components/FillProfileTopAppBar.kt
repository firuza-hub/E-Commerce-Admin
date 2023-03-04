package az.red.e_commerce_admin_android.ui.screens.fill_profile.components

import androidx.compose.foundation.layout.offset
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.themes.CustomTheme

@Composable
fun FillProfileTopAppBar(
    navController: NavController
) {
    TopAppBar(elevation = 0.dp, title = {
        Text(
            "Fill your profile",
            style = CustomTheme.typography.nunitoNormal18,
            modifier = Modifier.offset(x = (-16).dp),
            color = CustomTheme.colors.text
        )
    }, backgroundColor = CustomTheme.colors.background, navigationIcon = {
        IconButton(onClick = {
            navController.navigateUp()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = CustomTheme.colors.text
            )
        }
    })
}