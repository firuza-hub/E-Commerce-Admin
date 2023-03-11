package az.red.e_commerce_admin_android.ui.screens.create_product.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

@Composable
fun SelectItemDialog(
    modifier: Modifier = Modifier,
    dialogState: Boolean = false,
    onDialogStateChange: ((Boolean) -> Unit)? = null,
    onDismissRequest: (() -> Unit)? = null,
    onBrandItemClick: (String) -> Unit = {},
    onCategoryItemClick: (String) -> Unit = {},
    brandList: List<String> = emptyList(),
    categoryList: List<String> = emptyList(),
    type: DialogType
) {
    if (dialogState) {
        AlertDialog(
            onDismissRequest = {
                onDialogStateChange?.invoke(false)
                onDismissRequest?.invoke()
            },
            title = null,
            text = null,
            buttons = {
                when (type) {
                    DialogType.CATEGORY -> {
                        LazyColumn {
                            items(categoryList) { text ->
                                Text(
                                    text = text,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                        .clickable {
                                            onCategoryItemClick(text)
                                            onDialogStateChange?.invoke(false)
                                            onDismissRequest?.invoke()
                                        }
                                )
                            }
                        }
                    }
                    DialogType.BRAND -> {
                        LazyColumn {
                            items(brandList) { text ->
                                Text(
                                    text = text,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                        .clickable {
                                            onBrandItemClick(text)
                                            onDialogStateChange?.invoke(false)
                                            onDismissRequest?.invoke()
                                        }
                                )
                            }
                        }
                    }
                }
            },
            properties =
            DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
            modifier = modifier
                .fillMaxSize()
                .padding(top = 32.dp, bottom = 32.dp)
        )
    }
}

enum class DialogType {
    CATEGORY, BRAND
}