package az.red.e_commerce_admin_android.ui.screens.bottomnav.orders

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import az.red.e_commerce_admin_android.R
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.components.OrdersScreenBody
import az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.components.OrdersTopAppBar
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(
    navigateUp: () -> Unit,
    viewModel: OrderViewModel = koinViewModel()
) {
    val state = viewModel.orderList.collectAsState()
    val scrollState = rememberScrollState()



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

//            OutlinedButton(
//                onClick = { onMyProductsClick() },
//                colors = ButtonDefaults.buttonColors(CustomTheme.colors.cardBackground),
//                border = BorderStroke(
//                    1.dp,
//                    color = CustomTheme.colors.cardBorder
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(42.dp), shape = RoundedCornerShape(28.dp)
//            ) {
//                Text(
//                    text = stringResource(R.string.my_products),
//                    style = CustomTheme.typography.nunitoNormal18,
//                    color = CustomTheme.colors.text
//                )
//            }
        }
    }
}