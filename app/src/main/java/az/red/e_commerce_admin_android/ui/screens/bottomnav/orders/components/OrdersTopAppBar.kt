package az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.components

import androidx.compose.foundation.layout.offset
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.themes.CustomTheme

@Composable
fun OrdersTopAppBar(navigateUp: () -> Unit) {

    TopAppBar(elevation = 0.dp, title = {
        Text(
            text = stringResource(id = R.string.orders),
            style = CustomTheme.typography.nunitoNormal18,
            modifier = Modifier.offset(x = (-16).dp),
            color = CustomTheme.colors.text
        )
    }, backgroundColor = CustomTheme.colors.background, navigationIcon = {
        IconButton(onClick = {
            navigateUp()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = CustomTheme.colors.text
            )
        }
    }, actions = {
        IconButton(onClick = {/* Do Something*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = colorResource(id = R.color.accent_carrot)
            )
        }
    })
}