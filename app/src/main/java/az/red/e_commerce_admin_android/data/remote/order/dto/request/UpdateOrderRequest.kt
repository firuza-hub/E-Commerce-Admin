package az.red.e_commerce_admin_android.data.remote.order.dto.request

data class UpdateOrderRequest(
    val status : String,
    val letterSubject : String,
    val letterHtml : String,
    val email : String
)
