package az.red.e_commerce_admin_android.ui.screens.fill_profile.components

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
import az.red.e_commerce_admin_android.ui.themes.CustomTheme

@Composable
fun FillProfileButtons(onContinueClick: () -> Unit, onSkipClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp), verticalArrangement = Arrangement.SpaceBetween
    ) {

        Button(
            onClick = { onContinueClick() }, colors = ButtonDefaults.buttonColors(CustomTheme.colors.text),
            border = BorderStroke(
                1.dp,
                color =  CustomTheme.colors.cardBorder
            ),
            shape = RoundedCornerShape(28.dp),modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
        ) {
            Text(text = stringResource(R.string.btn_continue), style = CustomTheme.typography.nunitoNormal18, color = CustomTheme.colors.cardBackground)
        }

        OutlinedButton(
            onClick = { onSkipClick() },
            colors = ButtonDefaults.buttonColors(CustomTheme.colors.cardBackground),
            border = BorderStroke(
                1.dp,
                color =  CustomTheme.colors.cardBorder
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp), shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = stringResource(R.string.skip),
                style = CustomTheme.typography.nunitoNormal18,
                color = CustomTheme.colors.text
            )
        }
    }
}