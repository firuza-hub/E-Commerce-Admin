package az.red.e_commerce_admin_android.ui.screens.bottomnav.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.components.HomeTopAppBar
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.components.ProductListItem
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navigateUp: () -> Unit,
    productListViewModel: ProductListViewModel = koinViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        HomeTopAppBar(navigateUp)
        //Bottom 82.dp padding(BottomNav height size) -> LazyColumn last item didn't show because of BottomNav
        val items = productListViewModel.data.collectAsLazyPagingItems()


        if (items.loadState.append == LoadState.Loading || items.loadState.prepend == LoadState.Loading || items.loadState.refresh == LoadState.Loading) LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp), color = CustomTheme.colors.accent
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp, 0.dp, 0.dp, 82.dp)
        ) {
            items(items) {
                it?.let {
                    ProductListItem(it)
                }
            }
        }

    }
}


