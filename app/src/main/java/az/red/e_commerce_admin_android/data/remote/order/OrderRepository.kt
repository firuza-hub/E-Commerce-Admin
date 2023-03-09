package az.red.e_commerce_admin_android.data.remote.order

import az.red.e_commerce_admin_android.data.remote.order.dto.request.UpdateOrderRequest
import az.red.e_commerce_admin_android.data.remote.order.dto.response.order.Order
import az.red.e_commerce_admin_android.data.remote.order.dto.response.OrderResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    fun getOrders() : Flow<NetworkResult<List<OrderResponse>>>
    fun updateOrder(id : String,dto : UpdateOrderRequest) : Flow<NetworkResult<Order>>
}