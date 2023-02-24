package az.red.e_commerce_admin_android.ui.screens.bottomnav.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.components.HomeTopAppBar
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.components.ProductListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen( navController: NavController, productListViewModel:ProductListViewModel = koinViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        HomeTopAppBar(navController)

        //Bottom 82.dp padding(BottomNav height size) -> LazyColumn last item didn't show because of BottomNav
        val state = productListViewModel.getList().collectAsLazyPagingItems()

        LazyColumn(modifier = Modifier.fillMaxSize().padding(0.dp,0.dp,0.dp,82.dp)){
            items(state ){
                it?.let {
                    ProductListItem(it) }
            }
        }
    }
}

