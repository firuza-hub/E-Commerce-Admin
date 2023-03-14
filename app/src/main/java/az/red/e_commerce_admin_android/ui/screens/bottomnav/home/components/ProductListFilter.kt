package az.red.e_commerce_admin_android.ui.screens.bottomnav.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.data.remote.product.dto.request.ProductListItemRequest
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.ProductFilter
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.ProductFilterViewModel
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductListFilter(
    modifier: Modifier,
    viewModel: ProductFilterViewModel = koinViewModel(),
    closeFilter: (ProductListItemRequest) -> Unit
) {
    val selectedFilter by viewModel.selectedFilter.collectAsState()
    val selectedFilterOptions by viewModel.selectedFilterOptions.collectAsState()
    val request by viewModel.request.collectAsState()
    val filterItems = ProductFilter.values().toList()

    Card(
        modifier = modifier
            .height(400.dp)
            .padding(bottom = 70.dp),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Filter",
                    style = CustomTheme.typography.nunitoBold24,
                    modifier = Modifier.align(CenterVertically)
                )
                FiltersDropDown(
                    modifier = Modifier.align(CenterVertically),
                    filterItems = filterItems,
                    selectedFilter = selectedFilter,
                    onValueSelected = {
                        viewModel.onProductFilterValueChange(it)
                    })
            }
            Box(
                Modifier.fillMaxHeight()
                    .weight(weight = 1f, fill = false)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                ) {
                    items(selectedFilterOptions) {
                        Card(
                            shape = RoundedCornerShape(6.dp),
                            backgroundColor = if (it.isSelected) CustomTheme.colors.accent else CustomTheme.colors.cardBackground,
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clickable {
                                    viewModel.onFilterOptionSelected(it)
                                }
                        ) {
                            Text(text = it.name, modifier = Modifier.padding(4.dp))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(CustomTheme.spaces.medium))
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                    closeFilter(request)
                },
                    Modifier
                        .weight(1f)
                        .padding(4.dp)) {
                    Text(text = "Apply", color = CustomTheme.colors.btnText)

                }
                Button(onClick = {
                    viewModel.clearFilter()
                },
                    Modifier
                        .weight(1f)
                        .padding(4.dp)) {
                    Text(text = "Clear", color = CustomTheme.colors.btnText)
                }
            }
        }
    }


}

@Composable
fun FiltersDropDown(
    modifier: Modifier,
    filterItems: List<ProductFilter>,
    selectedFilter: ProductFilter,
    onValueSelected: (selectedFilter: ProductFilter) -> Unit
) {

    var ddExpanded by remember { mutableStateOf(false) }

    Box(
        modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        Row {
            Text(
                text = selectedFilter.name,
                modifier = Modifier
                    .wrapContentSize()
                    .clickable(onClick = { ddExpanded = true }),
                style = CustomTheme.typography.nunitoNormal16,
                color = CustomTheme.colors.hintText
            )
            Icon(
                modifier = Modifier.align(CenterVertically),
                painter = painterResource(id = R.drawable.ic_down),
                contentDescription = "Dropdown",
                tint = CustomTheme.colors.hintText
            )
        }

        DropdownMenu(
            expanded = ddExpanded,
            onDismissRequest = { ddExpanded = false }) {
            filterItems.forEachIndexed { _, productFilter ->
                DropdownMenuItem(onClick = {
                    ddExpanded = false
                    onValueSelected(productFilter)
                }) {
                    Text(text = productFilter.name)
                }
            }
        }

    }
}
