package az.red.e_commerce_admin_android.ui.screens.bottomnav.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.ui.common.bottomBorder
import az.red.e_commerce_admin_android.ui.themes.CustomTheme

@Composable
fun HomeTopAppBar(
    navigateUp: () -> Unit,
    searchInput: String,
    search: (input: String) -> Unit,
    switchShowFilter: (Boolean?) -> Unit,
) {

    var showSearchField by remember { mutableStateOf(false) }
    var searchInput by remember { mutableStateOf("") }

    TopAppBar(elevation = 0.dp, title = {
        if (showSearchField) ProductSearchField(
            searchInput,
            hideSearch = {
                searchInput = ""
                showSearchField = false
            },
            onValueChange = { searchInput = it },
            onDone = { if (showSearchField) search(searchInput) else showSearchField = true }
        )
        else
            Text(
                text = stringResource(id = R.string.my_products),
                style = CustomTheme.typography.nunitoNormal18,
                modifier = Modifier.offset(x = (-16).dp),
                color = CustomTheme.colors.text
            )
    }, backgroundColor = CustomTheme.colors.background, navigationIcon = {
        IconButton(onClick = {
            navigateUp()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = CustomTheme.colors.text
            )
        }
    }, actions = {
        IconButton(onClick = {
            if (showSearchField) search(searchInput) else {
                showSearchField = true
                switchShowFilter(false)
            }
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = colorResource(id = R.color.accent_carrot)
            )
        }
        IconButton(onClick = {
            switchShowFilter(null)
            showSearchField = false
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "Filter",
                tint = colorResource(id = R.color.accent_carrot)
            )
        }
    })
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProductSearchField(
    value: String,
    hideSearch: () -> Unit,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Row {
        BasicTextField(
            modifier = Modifier
                .offset(x = (-16).dp)
                .weight(1f)
                .align(CenterVertically),
            value = value,
            textStyle = CustomTheme.typography.nunitoNormal16,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                keyboardController?.hide()
                onDone()
            }),
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .bottomBorder(1.dp, CustomTheme.colors.accent),
                    contentAlignment = Alignment.BottomStart
                ) {
                    if (value.isEmpty()) Text(
                        text = "Search",
                        color = CustomTheme.colors.hintText,
                        style = CustomTheme.typography.nunitoNormal16
                    )
                    else
                        innerTextField()
                }
            }
        )
        IconButton(onClick = hideSearch, modifier = Modifier.align(CenterVertically)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                tint = colorResource(id = R.color.accent_carrot)
            )
        }
    }
}