package az.red.e_commerce_admin_android.ui.screens.review

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.components.ProductListImage
import az.red.e_commerce_admin_android.ui.screens.product_details.ProductDetailsState
import az.red.e_commerce_admin_android.ui.screens.review.components.ReviewListItemImage
import az.red.e_commerce_admin_android.ui.screens.review.components.ReviewListItemInfo
import az.red.e_commerce_admin_android.ui.screens.review.components.ReviewTopAppBar
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReviewScreen(
    popStackUp: () -> Unit,
    viewModel: ReviewViewModel = koinViewModel()
) {
    val reviewStateState = viewModel.review.collectAsState()
    val productDetailState = viewModel.productDetailState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoading) LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp), color = CustomTheme.colors.accent
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ReviewTopAppBar(popStackUp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = CustomTheme.spaces.large)
        ) {
            ReviewListItem(productDetailState.value)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = CustomTheme.spaces.medium)
            ) {
                itemsIndexed(reviewStateState.value.review) { reviewIndex, item ->
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(66.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${item.customer.firstName} ${item.customer.lastName}",
                                style = CustomTheme.typography.nunitoNormal18,
                                color = CustomTheme.colors.text
                            )

                            Text(
                                text = formatDate(item.product.date),
                                style = CustomTheme.typography.nunitoNormal12,
                                color = CustomTheme.colors.btnBackgroundInactive
                            )

                        }

                        Text(
                            text = item.content,
                            style = CustomTheme.typography.nunitoNormal16,
                            color = CustomTheme.colors.darkBtnBackground
                        )
                        Spacer(modifier = Modifier.height(CustomTheme.spaces.medium))

                        if (reviewStateState.value.review[reviewIndex].product.imageUrls.isNotEmpty()) {
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(97.dp)
                            ) {
                                itemsIndexed(reviewStateState.value.review[reviewIndex].product.imageUrls) { _, item ->
                                    ReviewListItemImage(item)
                                    Spacer(modifier = Modifier.width(CustomTheme.spaces.medium))
                                }
                            }
                        }
                    }
                }
            }
        }


    }
}

@Composable
fun ReviewListItem(state: ProductDetailsState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(CustomTheme.colors.background)
            .padding(vertical = CustomTheme.spaces.medium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(97.dp)
        ) {

            ProductListImage(imageUrls = state.imageUrls)
            ReviewListItemInfo(state)
        }
    }
}

fun formatDate(dateValue: String): String {
    val splitString = dateValue.split("T")
    val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val date = inputFormat.parse(splitString[0])
    return outputFormat.format(date!!)
}

