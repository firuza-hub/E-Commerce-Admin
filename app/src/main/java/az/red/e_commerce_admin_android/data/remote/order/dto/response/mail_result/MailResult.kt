package az.red.e_commerce_admin_android.data.remote.order.dto.response.mail_result

data class MailResult(
    val accepted : List<String>,
    val rejected : List<String>,
    val envelopTime : Double,
    val messageTime :Double,
    val messageSize :Double,
    val response : String,
    val envelope : Envelope,
    val messageId : String
)
