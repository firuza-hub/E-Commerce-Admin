package az.red.e_commerce_admin_android.data.remote.auth.dto.request

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val login: String,
    val email: String,
    val password: String,
    val isAdmin: Boolean
)


