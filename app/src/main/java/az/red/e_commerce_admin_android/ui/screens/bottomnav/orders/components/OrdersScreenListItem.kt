package az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.data.remote.order.dto.response.OrderResponse
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun OrdersScreenListItem(
    orderList: List<OrderResponse>,
    productIndex: Int,
    orderIndex: Int,
) {
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

            orderList[orderIndex].products[productIndex].product.imageUrls.let {
                if (it.any()) OrderListImage(
                    it.first()
                )
                OrderListInfo(orderList, orderIndex, productIndex)
            }
        }
    }
}

@Composable
fun OrderListImage(imgUrl: String) {
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
fun OrderListInfo(
    orderList: List<OrderResponse>,
    orderIndex: Int,
    productIndex: Int,
) {

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
                text = orderList[orderIndex].products[productIndex].product.name,
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
            text = "US $${orderList[orderIndex].products[productIndex].product.currentPrice}",
            style = CustomTheme.typography.nunitoBold14,
            modifier = Modifier.weight(1f),
            color = CustomTheme.colors.text
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
                border = BorderStroke(
                    1.dp,
                    color = if (orderList[orderIndex].canceled) CustomTheme.colors.orderStatusCancelledButton
                    else if (orderList[orderIndex].status == "shipped") CustomTheme.colors.orderStatusCompletedButton
                    else CustomTheme.colors.accent
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = CustomTheme.colors.btnText),
                shape = RoundedCornerShape(CustomTheme.spaces.small)
            ) {
                Text(
                    text = if (orderList[orderIndex].canceled) stringResource(id = R.string.cancelled)
                    else if (orderList[orderIndex].status == "shipped") stringResource(id = R.string.completed)
                    else stringResource(R.string.in_delivery),

                    style = CustomTheme.typography.nunitoNormal12,
                    color = if (orderList[orderIndex].canceled) CustomTheme.colors.orderStatusCancelledButton
                    else if (orderList[orderIndex].status == "shipped") CustomTheme.colors.orderStatusCompletedButton
                    else CustomTheme.colors.accent
                )
            }
        }

    }
}
