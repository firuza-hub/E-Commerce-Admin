package az.red.e_commerce_admin_android.ui.screens.create_product

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.screens.create_product.components.AddImageItem
import az.red.e_commerce_admin_android.ui.screens.create_product.components.CustomSimpleTextField
import az.red.e_commerce_admin_android.ui.screens.create_product.components.CustomTextView
import az.red.e_commerce_admin_android.ui.screens.login.AuthButtonColors
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateProduct(popBackStack: () -> Unit, viewModel: CreateProductViewModel = koinViewModel()) {
    SelectImageBottomSheet(popBackStack)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    popBackStack: () -> Unit,
    bottomSheetState: ModalBottomSheetState,
    images: List<Uri>
) {
    val scope = rememberCoroutineScope()

    val titleText = rememberSaveable { mutableStateOf(value = "") }
    val descriptionText = rememberSaveable { mutableStateOf(value = "") }
    val priceText = rememberSaveable { mutableStateOf(value = "") }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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
                IconButton(onClick = { popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null,
                        tint = CustomTheme.colors.text
                    )
                }
            })

        LazyRow {
            items(images) { uri ->
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp, 8.dp)
                        .size(160.dp)
                )
            }
        }
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
            }, hint = stringResource(id = R.string.price),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        CustomTextView(
            text = stringResource(id = R.string.brand),
            R.drawable.ic_brand,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        )

        CustomTextView(
            text = stringResource(id = R.string.category),
            R.drawable.ic_category,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        )

        CustomTextView(
            text = stringResource(id = R.string.subcategory), R.drawable.ic_sub_category,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 16.dp)
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
fun SelectImageBottomSheet(popBackStack: () -> Unit) {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    var selectImages by remember { mutableStateOf(listOf<Uri>()) }
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            selectImages = uriList
        }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                        .fillMaxWidth()
                        .clickable {
                            galleryLauncher.launch("image/*")
                        },
                    text = stringResource(id = R.string.gallery),
                    style = CustomTheme.typography.sansSerif20
                )
//                Text(
//                    modifier = Modifier
//                        .padding(top = 8.dp, bottom = 8.dp)
//                        .clickable { },
//                    text = stringResource(id = R.string.camera),
//                    style = CustomTheme.typography.sansSerif20
//                )
            }
        },
        sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        sheetElevation = 12.dp
    ) {
        MainContent(popBackStack, bottomSheetState, selectImages)
    }
}