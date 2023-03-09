package az.red.e_commerce_admin_android.ui.screens.bottomnav.orders

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import az.red.e_commerce_admin_android.R
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.components.OrdersScreenBody
import az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.components.OrdersTopAppBar
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(
    navigateUp: () -> Unit,
    viewModel: OrderViewModel = koinViewModel()
) {
    val state = viewModel.orderList.collectAsState()
    val scrollState = rememberScrollState()
    val orderSize = state.value.order.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp)
    ) {
        OrdersTopAppBar(navigateUp)

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(horizontal = CustomTheme.spaces.large, vertical = 25.dp)
        ) {

                OrdersScreenBody(
                    state.value.order.filter { it.status == "shipped" && !it.canceled },
                    "Completed",
                    R.drawable.ic_orders_ongoing,
                    "Completed is empty"
                )
                Spacer(modifier = Modifier.height(20.dp))

                OrdersScreenBody(
                    state.value.order.filter { it.status == "not shipped" && !it.canceled },
                    "Ongoing",
                    R.drawable.ic_orders_ongoing,
                    "Ongoing is empty"
                )
                Spacer(modifier = Modifier.height(20.dp))

                OrdersScreenBody(
                    state.value.order.filter { it.canceled },
                    "Cancelled",
                    R.drawable.ic_orders_ongoing,
                    "Cancelled is empty"
                )
        }
    }
}