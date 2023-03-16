package az.red.e_commerce_admin_android.ui.screens.bottomnav.orders

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import az.red.e_commerce_admin_android.R
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.components.OrdersScreenBody
import az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.components.OrdersTopAppBar
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(
    navigateUp: () -> Unit,
    navigateTo: (String) -> Unit,
    viewModel: OrderViewModel = koinViewModel()
) {
    val state = viewModel.orderList.collectAsState()
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    LaunchedEffect(true) {
        launch {
            viewModel.uiEventFlow.collect { event ->
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
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp)
    ) {
        OrdersTopAppBar(navigateUp)

        if (state.value.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp),
                color = CustomTheme.colors.accent,
            )
        }

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(horizontal = CustomTheme.spaces.large, vertical = 25.dp)
        ) {

            OrdersScreenBody(
                state.value.order.filter { it.status == stringResource(id = R.string.not_shipped) && !it.canceled },
                stringResource(R.string.ongoing),
                R.drawable.ic_orders_ongoing,
                stringResource(R.string.ongoing_empty_list_message)
            )
            Spacer(modifier = Modifier.height(20.dp))

            OrdersScreenBody(
                state.value.order.filter { it.status == stringResource(id = R.string.shipped) && !it.canceled },
                stringResource(R.string.completed),
                R.drawable.ic_orders_ongoing,
                stringResource(R.string.completed_empty_list_message)
            )
            Spacer(modifier = Modifier.height(20.dp))

            OrdersScreenBody(
                state.value.order.filter { it.canceled },
                stringResource(id = R.string.cancelled),
                R.drawable.ic_orders_ongoing,
                stringResource(R.string.cancelled_empty_list_message)
            )
        }
    }
}