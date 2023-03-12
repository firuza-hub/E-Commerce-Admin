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
import az.red.e_commerce_admin_android.ui.themes.CustomTheme

@Composable
fun ProductListFilter(modifier: Modifier) {
    val selectedFilterOptions = listOf("Black", "Purple", "Red", "Turquoise")
    val filterItems = listOf("Category", "Color", "Size", "Brand")

    Card(
        modifier = modifier.height(400.dp),
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
                    onValueSelected = {})
            }
            Box {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                ) {
                    items(selectedFilterOptions) {
                        Card(
                            shape = RoundedCornerShape(6.dp),
                            backgroundColor = CustomTheme.colors.cardBackground,
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            Text(text = it, modifier = Modifier.padding(4.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FiltersDropDown(
    modifier: Modifier,
    filterItems: List<String>,
    onValueSelected: (selectedFilter: String) -> Unit
) {

    var ddExpanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        Row {
            Text(
                filterItems[selectedIndex],
                modifier = Modifier
                    .wrapContentSize()
                    .clickable(onClick = { ddExpanded = true }),
                style = CustomTheme.typography.nunitoNormal16,
                color = CustomTheme.colors.hintText
            )
            Icon(
                modifier = Modifier.align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.ic_down),
                contentDescription = "Dropdown",
                tint = CustomTheme.colors.hintText
            )
        }

        DropdownMenu(
            expanded = ddExpanded,
            onDismissRequest = { ddExpanded = false }) {
            filterItems.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    ddExpanded = false
                    onValueSelected(s)
                }) {
                    Text(text = s)
                }
            }
        }

    }
}
