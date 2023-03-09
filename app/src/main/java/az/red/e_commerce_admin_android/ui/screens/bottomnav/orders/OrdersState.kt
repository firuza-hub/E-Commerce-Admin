package az.red.e_commerce_admin_android.ui.screens.bottomnav.orders

import az.red.e_commerce_admin_android.data.remote.order.dto.response.OrderResponse

data class OrdersState(
    val order: List<OrderResponse> = emptyList(),
    val isChangeOrderStatusSuccessful: Boolean = false,
    val isLoading: Boolean = false
)
