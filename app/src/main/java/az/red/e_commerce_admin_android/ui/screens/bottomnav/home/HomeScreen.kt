package az.red.e_commerce_admin_android.ui.screens.bottomnav.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.components.HomeTopAppBar
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.components.ProductListItem
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navigateUp: () -> Unit,
    navigateTo: (route:String) -> Unit,
    productListViewModel: ProductListViewModel = koinViewModel()
) {
    val context = LocalContext.current
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


