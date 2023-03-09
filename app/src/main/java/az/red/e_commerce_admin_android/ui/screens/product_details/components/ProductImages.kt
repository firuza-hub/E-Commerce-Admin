package az.red.e_commerce_admin_android.ui.screens.product_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductImagesCarousel(imageUrls: List<String>, modifier: Modifier, discount: Int) {
    val state = rememberPagerState(initialPage = 1)

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            HorizontalPager(count = imageUrls.size, state = state) { page ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(imageUrls[page])
                        .crossfade(true).build(),
                    modifier = modifier.fillMaxWidth(),
                    contentDescription = "product images",
                    contentScale = ContentScale.Crop
                )
            }
            if (discount > 0) {
                DiscountCard(
                    modifier = Modifier
                        .padding(16.dp).width(60.dp).height(30.dp)
                        .align(Alignment.BottomEnd), discount = discount,
                    textStyle = CustomTheme.typography.nunitoBold14
                )
            }
        }

        DotsIndicator(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(vertical = 16.dp),
            totalDots = imageUrls.size,
            state.currentPage,
            CustomTheme.colors.accent,
            CustomTheme.colors.inputIconHint
        )

    }


}

@Composable
fun DotsIndicator(
    modifier: Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {

    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()

    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
    }
}