package az.red.e_commerce_admin_android.ui.screens.create_product.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.ui.themes.CustomTheme

@Composable
fun CustomSimpleTextField(
    modifier: Modifier, onValueChangeUnit: (String) -> Unit, text: String = "", hint: String = ""
) {
    var textState by remember { mutableStateOf(text) }
    OutlinedTextField(modifier = modifier
        .fillMaxWidth(),
        value = textState,
        onValueChange = {
            textState = it
            onValueChangeUnit.invoke(it)
        },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = CustomTheme.colors.background,
            cursorColor = Color.Black,
            disabledLabelColor = Color.Black,
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = CustomTheme.colors.imageCardBackground
        ),
        placeholder = { Text(hint, color = CustomTheme.colors.hintText) },
        singleLine = true
    )
}

