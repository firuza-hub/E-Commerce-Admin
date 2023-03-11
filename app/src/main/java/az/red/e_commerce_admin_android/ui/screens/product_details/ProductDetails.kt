package az.red.e_commerce_admin_android.ui.screens.product_details

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.navigation.main.home.HomeNavScreen
import az.red.e_commerce_admin_android.ui.screens.product_details.components.ProductDetailsTopAppBar
import az.red.e_commerce_admin_android.ui.screens.product_details.components.ProductImagesCarousel
import az.red.e_commerce_admin_android.ui.screens.product_details.components.ProductInfoRedirectLine
import az.red.e_commerce_admin_android.ui.screens.product_details.components.SimilarGoodsCarousel
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductDetails(
    popBackStack: () -> Unit,
    navigateTo: (String) -> Unit,
    navigateToReviews: (String) -> Unit,
    viewModel: ProductDetailsViewModel = koinViewModel()
) {
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    LaunchedEffect(true) {
        launch {
            viewModel.uiEventFlow.collect { event ->
                when (event) {
                    is UIEvent.Error -> {
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    }
                    is UIEvent.Message -> {
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    }
                    is UIEvent.Navigate -> {
                        navigateTo(event.route)
                    }
                }
            }
        }
    }

    val state by viewModel.state.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val similarProducts by viewModel.similarProducts.collectAsState()

    if (!isLoading && state.error == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            if (state.imageUrls.any())
                ProductImagesCarousel(
                    imageUrls = state.imageUrls,
                    modifier = Modifier.height(375.dp),
                    state.discount
                )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = CustomTheme.spaces.large)
            ) {

                Text(
                    text = state.name,
                    style = CustomTheme.typography.nunitoBold18,
                    color = CustomTheme.colors.text
                )
                Text(
                    modifier = Modifier.padding(
                        vertical = CustomTheme.spaces.medium
                    ),
                    text = state.description, style = CustomTheme.typography.nunitoNormal16,
                    color = CustomTheme.colors.text
                )
                Row(
                    modifier = Modifier
                        .padding(
                            vertical = CustomTheme.spaces.medium
                        )
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "US $${state.currentPrice}",
                        style = CustomTheme.typography.nunitoExtraBold14,
                        color = CustomTheme.colors.text,
                        modifier = Modifier.padding(
                            end = CustomTheme.spaces.large
                        )
                    )
                    if (state.previousPrice != state.currentPrice) {
                        Text(
                            text = "US $${state.previousPrice}",
                            style = CustomTheme.typography.nunitoNormal14StrikeThrough,
                            color = CustomTheme.colors.text

                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                ProductInfoRedirectLine(stringResource(R.string.product_specifications), {})
                ProductInfoRedirectLine(stringResource(R.string.product_reviews)) {
                    navigateToReviews(
                        state.id
                    )
                }
                ProductInfoRedirectLine(stringResource(R.string.product_questions), {})

                Spacer(Modifier.height(16.dp))
                if (similarProducts.any()) {
                    println("MEOW if")
                    SimilarGoodsCarousel(
                        similarProducts,
                        redirectTo = { navigateTo(HomeNavScreen.ProductDetails.route + "/${it}") })
                }
            }
        }
        ProductDetailsTopAppBar(popBackStack)
    }
}