package az.red.e_commerce_admin_android.ui.screens.product_details.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.ui.themes.CustomTheme

@Composable
fun DiscountCard(modifier :Modifier, discount:Int, textStyle: TextStyle) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            contentColor = CustomTheme.colors.text,
            containerColor = CustomTheme.colors.accent
        ),
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(
                text = "-${discount}%",
                style = textStyle,
                color = CustomTheme.colors.btnTextAlwaysLight
            )
        }
    }
}