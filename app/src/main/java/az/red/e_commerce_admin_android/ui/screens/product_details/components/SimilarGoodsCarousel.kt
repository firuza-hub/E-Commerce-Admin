package az.red.e_commerce_admin_android.ui.screens.product_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.screens.product_details.ProductModel
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun SimilarGoodsCarousel(
    similarProducts: List<ProductModel>,
    redirectTo: (itemNo: String) -> Unit
) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = stringResource(R.string.product_similar_goods),
            style = CustomTheme.typography.nunitoBold18,
            color = CustomTheme.colors.text
        )
        Text(
            text = stringResource(R.string.see_all),
            style = CustomTheme.typography.nunitoNormal14,
            color = CustomTheme.colors.accent
        )
    }

    Spacer(modifier = Modifier.height(CustomTheme.spaces.medium))

    LazyRow {
        items(similarProducts) {
            Column(modifier = Modifier
                .width(160.dp)
                .padding(end = 10.dp)
                .clickable { redirectTo(it.itemNo) }) {
                Box {
                    if (it.imageUrls.any()) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current).data(it.imageUrls[0])
                                .crossfade(true).build(),
                            modifier = Modifier
                                .height(160.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp)),
                            contentDescription = "similar goods item",
                            contentScale = ContentScale.Crop
                        )
                    } else Image(
                        modifier = Modifier.height(160.dp).fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
                        painter = painterResource(id = R.drawable.no_image),
                        contentDescription = "No image",
                        contentScale = ContentScale.Crop
                    )
                    if (it.discount > 0) {
                        DiscountCard(
                            modifier = Modifier
                                .width(55.dp)
                                .height(30.dp)
                                .padding(4.dp)
                                .align(Alignment.TopEnd), discount = it.discount,
                            textStyle = CustomTheme.typography.nunitoNormal12
                        )
                    }
                }

                Text(
                    text = it.name,
                    modifier = Modifier.padding(top = 8.dp),
                    style = CustomTheme.typography.nunitoSemiBold16,
                    color = CustomTheme.colors.text
                )
                Text(
                    text = it.description, style = CustomTheme.typography.nunitoNormal14,
                    color = CustomTheme.colors.text
                )
                Text(
                    text = "US $${it.currentPrice}", modifier = Modifier
                        .padding(top = 8.dp), style = CustomTheme.typography.nunitoBold14,
                    color = CustomTheme.colors.text
                )
                if (it.previousPrice!= null && it.previousPrice != it.currentPrice) {
                    Text(
                        text = "US $${it.previousPrice}",
                        style = CustomTheme.typography.nunitoNormal14StrikeThrough,
                        color = CustomTheme.colors.text
                    )
                }
                Spacer(modifier = Modifier.height(CustomTheme.spaces.extraLarge))
            }
        }
    }

}