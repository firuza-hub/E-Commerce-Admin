package az.red.e_commerce_admin_android.ui.screens.bottomnav.orders

import android.util.Log
import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.order.OrderRepository
import az.red.e_commerce_admin_android.data.remote.order.dto.request.UpdateOrderRequest
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class OrderViewModel(
    private val repository: OrderRepository
) : BaseViewModel() {

    var orderList = MutableStateFlow(OrdersState())

    init {
        getOrderList()
    }

    private fun getOrderList() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getOrders().collect {
                when (it) {
                    is NetworkResult.Success -> {
                        triggerEvent(UIEvent.Message("Success!"))

                        orderList.value = orderList.value.copy(
                            order = it.data!!, isLoading = false
                        )
                    }
                    is NetworkResult.Empty -> Log.i("ORDER_REQUEST", "Empty")
                    is NetworkResult.Error -> {
                        Log.i("ORDER_REQUEST", "Error: ${it.message}")
                        it.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                    }
                    is NetworkResult.Exception -> {
                        Log.e("ORDER_REQUEST", "Exception: ${it.message}")
                        it.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                    }
                    is NetworkResult.Loading -> {
                        orderList.value = OrdersState(isLoading = true)
                        Log.i("ORDER_REQUEST", "Loading")
                    }
                }
            }
        }
    }

    fun onUiEvent(orderUiEvent: OrderUiEvent) {
        when (orderUiEvent) {
            is OrderUiEvent.ChangeStatusButton -> {
                val body = UpdateOrderRequest(
                    orderUiEvent.orderStatus,
                    "Your order has been shipped!",
                    "Your order has been shipped.OrderNo is ${orderUiEvent.orderNO}",
                    orderUiEvent.mail
                )
                changeOrderStatus(orderUiEvent.orderId,body)
                orderList.value = orderList.value.copy(isChangeOrderStatusSuccessful = true)
            }
        }
    }

    private fun changeOrderStatus(orderId: String,dto : UpdateOrderRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateOrder(orderId,dto).collect {
                when (it) {
                    is NetworkResult.Success -> {
                        getOrderList()
                        triggerEvent(UIEvent.Message("Success!"))
                    }
                    is NetworkResult.Empty -> Log.i("CHANGE_ORDER_STATUS", "Empty")
                    is NetworkResult.Error -> {
                        Log.i("CHANGE_ORDER_STATUS", "Error: ${it.message}")
                        it.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                    }
                    is NetworkResult.Exception -> {
                        Log.e("CHANGE_ORDER_STATUS", "Exception: ${it.message}")
                        it.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                    }
                    is NetworkResult.Loading -> {
                        orderList.value = OrdersState(isLoading = true)
                        Log.i("CHANGE_ORDER_STATUS", "Loading")
                    }
                }
            }
        }
    }

}