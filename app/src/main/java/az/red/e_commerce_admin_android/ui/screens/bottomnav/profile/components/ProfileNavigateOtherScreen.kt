package az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.noRippleClickable
import az.red.e_commerce_admin_android.ui.themes.CustomTheme

@Composable
fun ProfileNavigateOtherScreen(
    icon: Int,
    title: String,
    onItemCLick: () -> Unit,
    isLogOut: Boolean = false
) {

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .noRippleClickable {
                    onItemCLick()
                }
                .fillMaxWidth()
                .height(32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.fillMaxHeight()) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = title,
                    tint = if (isLogOut) CustomTheme.colors.accent else CustomTheme.colors.text
                )
                Text(
                    text = title,
                    style = CustomTheme.typography.nunitoNormal16,
                    modifier = Modifier.padding(horizontal = 13.dp),
                    color = if (isLogOut) CustomTheme.colors.accent else CustomTheme.colors.text
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_right),
                contentDescription = "Right Arrow",
                tint = CustomTheme.colors.text
            )
        }

    }

}