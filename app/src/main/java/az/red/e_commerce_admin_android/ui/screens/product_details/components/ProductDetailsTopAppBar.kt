package az.red.e_commerce_admin_android.ui.screens.product_details.components

import androidx.compose.foundation.layout.offset
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.themes.CustomTheme

@Composable
fun ProductDetailsTopAppBar(navigateUp: () -> Unit) {

    TopAppBar(elevation = 0.dp,
        backgroundColor = Color.Transparent,
        navigationIcon = {
            IconButton(onClick = {
                navigateUp()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = CustomTheme.colors.text
                )
            }
        },
        actions = {
            IconButton(onClick = {/* Do Something*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = null,
                    tint = colorResource(id = R.color.accent_carrot)
                )
            }
        },
        title = {})
}