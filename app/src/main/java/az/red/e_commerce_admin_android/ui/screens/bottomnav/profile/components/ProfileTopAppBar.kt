package az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.components

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.noRippleClickable
import az.red.e_commerce_admin_android.ui.themes.CustomTheme

@Composable
fun ProfileTopAppBar(navigateUp:()->Unit) {

    TopAppBar(elevation = 0.dp, title = {
        Text(
            stringResource(id = R.string.profile),
            style = CustomTheme.typography.nunitoNormal18,
            modifier = Modifier.offset(x = (-16).dp),
            color = CustomTheme.colors.text
        )
    }, backgroundColor = CustomTheme.colors.background, navigationIcon = {
        IconButton(onClick = navigateUp ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = CustomTheme.colors.text
            )
        }
    }, actions = {

        Text(text = stringResource(id = R.string.edit_profile),
            style = CustomTheme.typography.nunitoNormal16,
            color = CustomTheme.colors.accent,
            modifier = Modifier
                .padding(0.dp, 0.dp, CustomTheme.spaces.large, 0.dp)
                .noRippleClickable {
                    /* Do Something */
                })

    })
}