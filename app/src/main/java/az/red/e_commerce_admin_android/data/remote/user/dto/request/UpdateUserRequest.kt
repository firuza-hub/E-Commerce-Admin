package az.red.e_commerce_admin_android.data.remote.user.dto.request

data class UpdateUserRequest(
    val firstName: String,
    val lastName:String,
    val login: String,
    val email: String
)