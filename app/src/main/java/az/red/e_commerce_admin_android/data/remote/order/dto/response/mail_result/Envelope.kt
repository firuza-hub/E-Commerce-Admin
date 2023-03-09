package az.red.e_commerce_admin_android.data.remote.order.dto.response.mail_result

data class Envelope(
    val from : String,
    val to : List<String>
)
