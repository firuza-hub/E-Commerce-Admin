package az.red.e_commerce_admin_android.data.remote.order

import az.red.e_commerce_admin_android.data.remote.EndPoints
import az.red.e_commerce_admin_android.data.remote.order.dto.request.UpdateOrderRequest
import az.red.e_commerce_admin_android.data.remote.order.dto.response.order.Order
import az.red.e_commerce_admin_android.data.remote.order.dto.response.OrderResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface OrderService {

    @GET(EndPoints.GET_ORDERS)
    suspend fun getOrders() : Response<List<OrderResponse>>

    @PUT(EndPoints.UPDATE_ORDER)
    suspend fun updateOrder(
        @Path("id") id : String,
        @Body dto : UpdateOrderRequest
    ) : Response<Order>

}