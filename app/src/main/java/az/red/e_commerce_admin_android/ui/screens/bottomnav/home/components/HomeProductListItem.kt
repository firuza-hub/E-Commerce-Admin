package az.red.e_commerce_admin_android.ui.screens.bottomnav.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductResponse
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.ProductListViewModel
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductListItem(productResponse: ProductResponse, onClick: (id: String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(CustomTheme.colors.background)
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .clickable { onClick(productResponse._id) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(97.dp)
        ) {

            ProductListImage(productResponse.imageUrls)
            ProductListInfo(productResponse)
        }
    }
}

@Composable
fun ProductListImage(imageUrls: List<String>) {
    Box(
        modifier = Modifier
            .size(width = 97.dp, height = 97.dp)
            .clip(RoundedCornerShape(size = 8.dp))
            .background(CustomTheme.colors.btnText)
    ) {
        if (imageUrls.any()) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(imageUrls.first())
                    .crossfade(true)
                    .build(),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Product Image",
                placeholder = painterResource(id = R.drawable.no_image),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.no_image),
                contentDescription = "No Image",
                contentScale = ContentScale.Crop
            )
        }

    }
}

@Composable
fun ProductListInfo(
    productResponse: ProductResponse,
    viewModel: ProductListViewModel = koinViewModel()
) {
    var isDeactivate by rememberSaveable {
        mutableStateOf(productResponse.enabled)
    }

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
                text = productResponse.name,
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
            text = "US $${productResponse.currentPrice}",
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
                    isDeactivate = !isDeactivate
                    viewModel.deactivateProduct(productResponse, isDeactivate)
                },
                border = BorderStroke(1.dp, CustomTheme.colors.cardBorder),
                colors = ButtonDefaults.buttonColors(backgroundColor = CustomTheme.colors.background),
                shape = RoundedCornerShape(CustomTheme.spaces.large)
            ) {
                Text(
                    text = if (isDeactivate) stringResource(R.string.deactivate) else stringResource(
                        R.string.activate
                    ),
                    style = CustomTheme.typography.nunitoNormal12,
                    color = CustomTheme.colors.text
                )
            }

            Button(
                modifier = Modifier.weight(1f), onClick = {
                    //your onclick code
                }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = CustomTheme.colors.darkBtnBackground,
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
