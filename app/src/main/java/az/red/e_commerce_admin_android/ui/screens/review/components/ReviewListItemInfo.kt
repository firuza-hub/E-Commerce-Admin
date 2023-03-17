package az.red.e_commerce_admin_android.ui.screens.review.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.ui.screens.product_details.ProductDetailsState
import az.red.e_commerce_admin_android.ui.themes.CustomTheme

@Composable
fun ReviewListItemInfo(
    state: ProductDetailsState,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp, 0.dp, 0.dp)
    ) {

        Text(
            text = state.name,
            style = CustomTheme.typography.nunitoNormal16,
            color = CustomTheme.colors.text
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "US $${state.currentPrice}",
                style = CustomTheme.typography.nunitoBold14,
                color = CustomTheme.colors.text
            )

            Spacer(modifier = Modifier.width(CustomTheme.spaces.large))

            if (state.previousPrice != state.currentPrice && state.previousPrice != null) {
                Text(
                    text = "US $${state.previousPrice}",
                    style = CustomTheme.typography.nunitoNormal14StrikeThrough,
                    color = CustomTheme.colors.text

                )
            }
        }
    }
}