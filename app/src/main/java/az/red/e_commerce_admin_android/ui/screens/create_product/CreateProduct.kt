package az.red.e_commerce_admin_android.ui.screens.create_product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.screens.create_product.components.AddImageItem
import az.red.e_commerce_admin_android.ui.screens.create_product.components.CustomSimpleTextField
import az.red.e_commerce_admin_android.ui.screens.create_product.components.CustomTextView
import az.red.e_commerce_admin_android.ui.screens.login.AuthButtonColors
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import kotlinx.coroutines.launch

@Composable
fun CreateProduct(navController: NavController) {
    SelectImageBottomSheet(navController)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(navController: NavController, bottomSheetState: ModalBottomSheetState) {
    val scope = rememberCoroutineScope()

    val titleText = rememberSaveable { mutableStateOf(value = "") }
    val descriptionText = rememberSaveable { mutableStateOf(value = "") }
    val priceText = rememberSaveable { mutableStateOf(value = "") }

    Column {
        TopAppBar(
            elevation = 0.dp,
            title = {
                Text(
                    stringResource(id = R.string.add_new_product),
                    style = CustomTheme.typography.nunitoNormal18,
                    modifier = Modifier.offset(x = (-16).dp),
                    color = CustomTheme.colors.text
                )
            },
            backgroundColor = CustomTheme.colors.background,
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null,
                        tint = CustomTheme.colors.text
                    )
                }
            })

        AddImageItem(modifier = Modifier
            .padding(start = 16.dp, top = 16.dp)
            .clickable {
                scope.launch {
                    if (bottomSheetState.isVisible) bottomSheetState.hide() else bottomSheetState.show()
                }
            })

        CustomSimpleTextField(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            onValueChangeUnit = {
                titleText.value = it
            }, hint = stringResource(id = R.string.title)
        )

        CustomSimpleTextField(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                .fillMaxWidth(),
            onValueChangeUnit = {
                descriptionText.value = it
            }, hint = stringResource(id = R.string.description)
        )

        CustomSimpleTextField(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                .fillMaxWidth(),
            onValueChangeUnit = {
                priceText.value = it
            }, hint = stringResource(id = R.string.price)
        )

        CustomTextView(
            text = "Brand",
            R.drawable.ic_brand,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        )

        CustomTextView(
            text = "Category",
            R.drawable.ic_category,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        )

        CustomTextView(
            text = "Subcategory", R.drawable.ic_sub_category,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                .height(45.dp)
                .clip(RoundedCornerShape(28.dp)),
            colors = AuthButtonColors(),
            onClick = { },
            enabled = true
        ) {
            Text(
                text = stringResource(id = R.string._continue),
                style = CustomTheme.typography.nunitoBold18
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectImageBottomSheet(navController: NavController) {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = "Gallery")
                Text(text = "Camera")
            }
        },
        sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        sheetElevation = 12.dp
    ) {
        MainContent(navController, bottomSheetState)
    }
}