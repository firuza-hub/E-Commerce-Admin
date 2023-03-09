package az.red.e_commerce_admin_android.data.remote.order

import az.red.e_commerce_admin_android.data.remote.order.dto.request.UpdateOrderRequest
import az.red.e_commerce_admin_android.data.remote.order.dto.response.order.Order
import az.red.e_commerce_admin_android.data.remote.order.dto.response.OrderResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.handleApi
import kotlinx.coroutines.flow.Flow

class OrderRepositoryImpl(
    private val orderService: OrderService
) : OrderRepository {

    override fun getOrders(): Flow<NetworkResult<List<OrderResponse>>> {
        return handleApi { orderService.getOrders() }
    }

    override fun updateOrder(id: String,dto : UpdateOrderRequest): Flow<NetworkResult<Order>> {
        return handleApi { orderService.updateOrder(id,dto) }
    }
}