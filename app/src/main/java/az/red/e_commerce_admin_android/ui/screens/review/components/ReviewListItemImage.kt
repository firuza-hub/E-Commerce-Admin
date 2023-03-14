package az.red.e_commerce_admin_android.ui.screens.review.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ReviewListItemImage(imgUrl: String) {
    Box(
        modifier = Modifier
            .size(width = 97.dp, height = 97.dp)
            .clip(RoundedCornerShape(size = 8.dp))
            .background(CustomTheme.colors.btnText)
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(imgUrl).crossfade(true).build(),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "productListItemImage",
            contentScale = ContentScale.Fit
        )
    }
}
