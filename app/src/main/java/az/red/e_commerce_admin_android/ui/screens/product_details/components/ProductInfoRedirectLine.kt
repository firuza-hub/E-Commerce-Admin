package az.red.e_commerce_admin_android.ui.screens.product_details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.themes.CustomTheme

@Composable
fun ProductInfoRedirectLine(text: String, redirectTo: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { redirectTo() }) {
        Text(
            text = text,
            style = CustomTheme.typography.nunitoSemiBold16,
            modifier = Modifier.padding(vertical = 16.dp),
            color = CustomTheme.colors.text
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = "arrow",
            modifier = Modifier.align(
                Alignment.CenterVertically
            ),
            tint = CustomTheme.colors.text
        )
    }
}