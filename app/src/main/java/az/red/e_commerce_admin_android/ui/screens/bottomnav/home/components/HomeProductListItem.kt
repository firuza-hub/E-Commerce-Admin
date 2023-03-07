package az.red.e_commerce_admin_android.ui.screens.bottomnav.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductListItemResponse
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ProductListItem(productListItemResponse: ProductListItemResponse) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(CustomTheme.colors.background)
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(97.dp)
        ) {

            productListItemResponse.imageUrls.let { if (it.any()) ProductListImage(it.first()) }
            ProductListInfo(productListItemResponse)
        }
    }
}

@Composable
fun ProductListImage(imgUrl: String) {
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

@Composable
fun ProductListInfo(productListItemResponse: ProductListItemResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp, 0.dp, 0.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = productListItemResponse.name,
                style = CustomTheme.typography.nunitoNormal12,
                color = CustomTheme.colors.text
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = "productListItemIconSettings",
                tint = CustomTheme.colors.accent
            )
        }

        Text(
            text = "US $${productListItemResponse.currentPrice}",
            style = CustomTheme.typography.nunitoBold14,
            color = CustomTheme.colors.text,
            modifier = Modifier.weight(1f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Button(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(1f),
                onClick = {
                    //your onclick code
                },
                border = BorderStroke(1.dp, CustomTheme.colors.cardBorder),
                colors = ButtonDefaults.buttonColors(backgroundColor = CustomTheme.colors.background),
                shape = RoundedCornerShape(CustomTheme.spaces.large)
            ) {
                Text(
                    text = stringResource(R.string.deactivate),
                    style = CustomTheme.typography.nunitoNormal12,
                    color = CustomTheme.colors.text
                )
            }

            Button(
                modifier = Modifier.weight(1f), onClick = {
                    //your onclick code
                }, colors = ButtonDefaults.buttonColors(backgroundColor = CustomTheme.colors.darkBtnBackground,
                ), shape = RoundedCornerShape(CustomTheme.spaces.large)
            ) {
                Text(
                    text = stringResource(R.string.statistics),
                    style = CustomTheme.typography.nunitoNormal12,
                    color = CustomTheme.colors.btnTextAlwaysLight
                )
            }
        }

    }
}
