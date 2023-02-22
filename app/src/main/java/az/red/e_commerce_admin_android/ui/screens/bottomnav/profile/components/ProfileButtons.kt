package az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.themes.*

@Composable
fun ProfileButtons(onAddNewProductClick: () -> Unit, onMyProductsClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp), verticalArrangement = Arrangement.SpaceBetween
    ) {

        Button(
            onClick = { onAddNewProductClick() }, colors = ButtonDefaults.buttonColors(BackgroundDark),
            shape = RoundedCornerShape(28.dp),modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
        ) {
            Text(text = stringResource(R.string.add_new_product), style = CustomTheme.typography.nunitoNormal18, color = ButtonTextWhite)
        }

        OutlinedButton(
            onClick = { onMyProductsClick() },
            border = BorderStroke(
                1.dp,
                color = BackgroundDark
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp), shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = stringResource(R.string.my_products),
                style = CustomTheme.typography.nunitoNormal18,
                color = InputCardBackgroundDark
            )
        }
    }
}