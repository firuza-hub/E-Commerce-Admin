package az.red.e_commerce_admin_android.ui.screens.bottomnav.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import az.red.e_commerce_admin_android.ui.navigation.main.home.HomeNavScreen
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.components.HomeTopAppBar
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.components.ProductListFilter
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.components.ProductListItem
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navigateUp: () -> Unit,
    navigateTo: (route: String) -> Unit,
    productListViewModel: ProductListViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val items = productListViewModel.data.collectAsLazyPagingItems()
    val searchInput by productListViewModel.searchInput.collectAsState()
    var showFilter by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        launch {
            productListViewModel.uiEventFlow.collect { event ->
                when (event) {
                    is UIEvent.Error -> {
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    }
                    is UIEvent.Message -> {
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    }
                    is UIEvent.Navigate -> {
                        navigateTo(event.route)
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            HomeTopAppBar(
                navigateUp,
                searchInput = searchInput,
                search = {
                    productListViewModel.getProductSearch(
                        it,
                        onComplete = { items.refresh() })
                },
                switchShowFilter = { showFilter = it ?:!showFilter })
            //Bottom 82.dp padding(BottomNav height size) -> LazyColumn last item didn't show because of BottomNav
            if (showFilter)
                Surface(
                    Modifier
                        .fillMaxSize()
                        .alpha(0.7f), color = Color.Black) {

                }
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
                    it?.let { item ->
                        ProductListItem(it) { navigateTo(HomeNavScreen.ProductDetails.route + "/${item.itemNo}") }
                    }
                }
            }

        }
        if (showFilter) {
            ProductListFilter(
                Modifier.align(Alignment.BottomStart)
            )
        }

    }
}



