package az.red.e_commerce_admin_android.ui.screens.bottomnav.orders

sealed class OrderUiEvent {
    data class ChangeStatusButton(
        val orderId: String,
        val orderStatus: String,
        val mail: String
    ) : OrderUiEvent()
}
