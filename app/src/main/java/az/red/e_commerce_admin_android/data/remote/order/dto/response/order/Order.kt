package az.red.e_commerce_admin_android.data.remote.order.dto.response.order

import az.red.e_commerce_admin_android.data.remote.order.dto.response.mail_result.MailResult
import az.red.e_commerce_admin_android.data.remote.order.dto.response.OrderResponse

data class Order(
    val order : OrderResponse,
    val mailResult : MailResult,
)
