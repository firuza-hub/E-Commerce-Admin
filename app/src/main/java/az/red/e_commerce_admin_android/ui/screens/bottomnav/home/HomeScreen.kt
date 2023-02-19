package az.red.e_commerce_admin_android.ui.screens.bottomnav.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.components.HomeTopAppBar
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.components.ProductListItem

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        HomeTopAppBar()
        
        LazyColumn(modifier = Modifier.fillMaxSize().padding(0.dp,0.dp,0.dp,82.dp)){
            items(20){
                ProductListItem()
            }
        }
    }
}

