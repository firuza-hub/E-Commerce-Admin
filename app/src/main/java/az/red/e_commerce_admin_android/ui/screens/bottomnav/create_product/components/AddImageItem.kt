package az.red.e_commerce_admin_android.ui.screens.bottomnav.create_product.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.themes.CustomTheme

@Composable
fun AddImageItem(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(160.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(CustomTheme.colors.imageCardBackground)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_add_image),
            contentDescription = "Add new image"
        )
    }
}