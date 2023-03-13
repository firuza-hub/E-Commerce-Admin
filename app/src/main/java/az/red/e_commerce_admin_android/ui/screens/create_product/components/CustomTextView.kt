package az.red.e_commerce_admin_android.ui.screens.create_product.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.themes.CustomTheme

@Composable
fun CustomTextView(
    text: String,
    selectedItemText: String,
    icon: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                1.dp,
                color = CustomTheme.colors.btnBackgroundActive,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = 12.dp, horizontal = 8.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.align(Alignment.CenterStart)) {
            Image(
                modifier = Modifier.padding(end = 12.dp),
                painter = painterResource(id = icon),
                contentDescription = "Item Text",
            )
            Text(text = text)
            Text(text = selectedItemText, modifier = Modifier.padding(start = 12.dp))
        }

        Image(
            modifier = Modifier.align(Alignment.CenterEnd),
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = "Next Image",
        )
    }
}