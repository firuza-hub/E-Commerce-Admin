package az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.data.remote.order.dto.response.OrderResponse
import az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.OrderUiEvent
import az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.OrderViewModel
import az.red.e_commerce_admin_android.ui.themes.CustomTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrdersScreenBody(
    orderList: List<OrderResponse>,
    text: String,
    ordersEmptyImage: Int,
    ordersEmptyMessage: String,
    viewModel: OrderViewModel = koinViewModel()
) {
    val shippingStatus = arrayOf(stringResource(id = R.string.shipped), stringResource(id = R.string.not_shipped))
    var selectedItem by remember {
        mutableStateOf(shippingStatus[0])
    }

    var expandedDropDownMenu by remember {
        mutableStateOf(false)
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    val downOrUpIcon = if (expanded) R.drawable.ic_down else R.drawable.ic_right


    Column(modifier = Modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .border(
                    border = BorderStroke(1.dp, CustomTheme.colors.inputIconHint),
                    shape = RoundedCornerShape(CustomTheme.spaces.medium)
                )
                .clickable {
                    expanded = !expanded
                }
        ) {

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = text,
                    style = CustomTheme.typography.montSerratSemiBold,
                    color = CustomTheme.colors.darkBtnBackground,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Icon(
                    painter = painterResource(id = downOrUpIcon),
                    tint = CustomTheme.colors.darkBtnBackground,
                    contentDescription = "Icon expandable card",
                    modifier = Modifier.padding(horizontal = 13.dp)
                )

            }

        }

        if (expanded) {

            if (orderList.isNotEmpty()) {
                for (orderIndex in orderList.indices)

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(330.dp)
                            .padding(vertical = 2.dp)
                    ) {
                        itemsIndexed(orderList) { _, _ ->

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .border(1.dp, color = CustomTheme.colors.accent)
                                    .clip(
                                        RoundedCornerShape(8.dp)
                                    )
                            ) {

                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {

                                   // For each orders create nested LazyColumn
                                    LazyColumn(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(250.dp)
                                    ) {
                                        itemsIndexed(orderList[orderIndex].products) { productIndex, _ ->
                                            OrdersScreenListItem(
                                                orderList = orderList,
                                                productIndex = productIndex,
                                                orderIndex = orderIndex
                                            )
                                        }
                                    }

                                    //If cancelled orders then not show change status button
                                    if (!orderList[orderIndex].canceled)
                                        ExposedDropdownMenuBox(
                                            expanded = expandedDropDownMenu,
                                            onExpandedChange = {
                                                if (!orderList[orderIndex].canceled)
                                                    expandedDropDownMenu = !expandedDropDownMenu
                                            }
                                        ) {

                                            Button(
                                                modifier = Modifier.weight(1.1f),
                                                onClick = {

                                                },
                                                colors = ButtonDefaults.buttonColors(
                                                    backgroundColor = CustomTheme.colors.darkBtnBackground,
                                                ),
                                                shape = RoundedCornerShape(CustomTheme.spaces.large)
                                            ) {
                                                Text(
                                                    text = stringResource(R.string.change_order_status),
                                                    style = CustomTheme.typography.nunitoNormal12,
                                                    color = CustomTheme.colors.textReverse
                                                )
                                            }

                                            ExposedDropdownMenu(
                                                expanded = expandedDropDownMenu,
                                                modifier = Modifier.background(CustomTheme.colors.accent),
                                                onDismissRequest = { expandedDropDownMenu = false }
                                            ) {
                                                shippingStatus.forEach { selectedOption ->
                                                    DropdownMenuItem(onClick = {
                                                        selectedItem = selectedOption

                                                        //Change order status and send mail with letterSubject and letterHtml
                                                        viewModel.onUiEvent(
                                                            orderUiEvent = OrderUiEvent.ChangeStatusButton(
                                                                orderList[orderIndex].id,
                                                                selectedItem,
                                                                orderList[orderIndex].orderNo,
                                                                orderList[orderIndex].email
                                                            )
                                                        )

                                                        expandedDropDownMenu = false
                                                    }) {
                                                        Text(
                                                            text = selectedOption,
                                                            style = CustomTheme.typography.nunitoNormal12,
                                                            color = CustomTheme.colors.btnText
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                }
                            }
                        }
                    }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = ordersEmptyImage),
                        contentDescription = "Ongoing empty image"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = ordersEmptyMessage,
                        style = CustomTheme.typography.nunitoNormal16,
                        color = CustomTheme.colors.darkBtnBackground
                    )
                }
            }
        }
    }
}
