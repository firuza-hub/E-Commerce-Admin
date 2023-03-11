package az.red.e_commerce_admin_android.ui.screens.create_product.components

import android.net.Uri
import android.widget.Toast
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.screens.create_product.CreateProductViewModel
import az.red.e_commerce_admin_android.ui.screens.login.AuthButtonColors
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.util.*

@Composable
fun CreateProduct(popBackStack: () -> Unit, viewModel: CreateProductViewModel = koinViewModel()) {
    SelectImageBottomSheet(popBackStack, viewModel)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    popBackStack: () -> Unit,
    bottomSheetState: ModalBottomSheetState,
    images: List<Uri>,
    viewModel: CreateProductViewModel
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val createProductState = viewModel.state.collectAsState()
    val brandList = viewModel.brandData.collectAsState().value
    val categoryList = viewModel.categoryData.collectAsState().value

    val titleText = rememberSaveable { mutableStateOf(value = "") }
    val descriptionText = rememberSaveable { mutableStateOf(value = "") }
    val priceText = rememberSaveable { mutableStateOf(value = 0.0) }
    val brandText = rememberSaveable { mutableStateOf(value = "") }
    val categoryText = rememberSaveable { mutableStateOf(value = "") }

    val dialogState = rememberSaveable { mutableStateOf(value = false) }
    val dialogType = rememberSaveable { mutableStateOf(value = DialogType.BRAND) }

    if (dialogState.value) {
        when (dialogType.value) {
            DialogType.CATEGORY -> {
                SelectItemDialog(dialogState = true, modifier = Modifier, onCategoryItemClick = {
                    categoryText.value = it
                }, categoryList = categoryList, type = dialogType.value, onDismissRequest = {
                    dialogState.value = false
                })
            }
            DialogType.BRAND -> {
                SelectItemDialog(dialogState = true, modifier = Modifier, onBrandItemClick = {
                    brandText.value = it
                }, brandList = brandList, type = dialogType.value, onDismissRequest = {
                    dialogState.value = false
                })
            }
        }
    }


    if (createProductState.value.error.isNotEmpty()) {
        Toast.makeText(context, createProductState.value.error, Toast.LENGTH_SHORT).show()
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        TopAppBar(elevation = 0.dp, title = {
            Text(
                stringResource(id = R.string.add_new_product),
                style = CustomTheme.typography.nunitoNormal18,
                modifier = Modifier.offset(x = (-16).dp),
                color = CustomTheme.colors.text
            )
        }, backgroundColor = CustomTheme.colors.background, navigationIcon = {
            IconButton(onClick = { popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = CustomTheme.colors.text
                )
            }
        })

        if (createProductState.value.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyRow {
                items(images) { uri ->
                    Image(
                        painter = rememberAsyncImagePainter(uri),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp, 8.dp)
                            .size(160.dp)
                            .clickable {
                                scope.launch {
                                    if (bottomSheetState.isVisible) bottomSheetState.hide() else bottomSheetState.show()
                                }
                            }
                    )
                }
            }

            if (images.isEmpty()) {
                AddImageItem(modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .clickable {
                        scope.launch {
                            if (bottomSheetState.isVisible) bottomSheetState.hide() else bottomSheetState.show()
                        }
                    })
            }

            CustomSimpleTextField(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                onValueChangeUnit = {
                    titleText.value = it
                },
                hint = stringResource(id = R.string.title)
            )

            CustomSimpleTextField(
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                    .fillMaxWidth(),
                onValueChangeUnit = {
                    descriptionText.value = it
                },
                hint = stringResource(id = R.string.description)
            )

            CustomSimpleTextField(
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                    .fillMaxWidth(),
                onValueChangeUnit = {
                    priceText.value = it.toDouble()
                },
                hint = stringResource(id = R.string.price),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            CustomTextView(text = stringResource(id = R.string.brand),
                R.drawable.ic_brand,
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                    .clickable {
                        dialogState.value = true
                        dialogType.value = DialogType.BRAND
                    })

            CustomTextView(text = stringResource(id = R.string.category),
                R.drawable.ic_category,
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                    .clickable {
                        dialogState.value = true
                        dialogType.value = DialogType.CATEGORY
                    })

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 16.dp)
                    .height(45.dp)
                    .clip(RoundedCornerShape(28.dp)), colors = AuthButtonColors(), onClick = {
                    viewModel.createProduct(
                        brand = brandText.value,
                        categories = categoryText.value,
                        myCustomParam = descriptionText.value,
                        currentPrice = priceText.value,
                        imagesPath = images,
                        name = titleText.value
                    )
                }, enabled = true
            ) {
                Text(
                    text = stringResource(id = R.string._continue),
                    style = CustomTheme.typography.nunitoBold18
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectImageBottomSheet(popBackStack: () -> Unit, viewModel: CreateProductViewModel) {
    val scope = rememberCoroutineScope()
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    var selectImages by remember { mutableStateOf(listOf<Uri>()) }
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            selectImages = uriList
        }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState, sheetContent = {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                        .fillMaxWidth()
                        .clickable {
                            galleryLauncher.launch("image/*")
                            scope.launch { bottomSheetState.hide() }
                        },
                    text = stringResource(id = R.string.gallery),
                    style = CustomTheme.typography.sansSerif20
                )
            }
        }, sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp), sheetElevation = 12.dp
    ) {
        MainContent(popBackStack, bottomSheetState, selectImages, viewModel)
    }
}