package az.red.e_commerce_admin_android.ui.screens.product_details.components

import android.content.Intent
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.screens.product_details.ProductDetailsState
import az.red.e_commerce_admin_android.ui.themes.CustomTheme

@Composable
fun ProductDetailsTopAppBar(navigateUp: () -> Unit, state: ProductDetailsState) {
    val mContext = LocalContext.current
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
            IconButton(onClick = { mContext.startActivity(shareIntent(state)) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = null,
                    tint = colorResource(id = R.color.accent_carrot)
                )
            }
        },
        title = {}
    )
}

fun shareIntent(state: ProductDetailsState): Intent {
    return Intent.createChooser(Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_SUBJECT, "Check this out!")
        putExtra(Intent.EXTRA_TEXT, "${state.name}\nDescription: ${state.description}\nPrice: US $${state.currentPrice}")
        type = "text/plain"
    }, null)
}