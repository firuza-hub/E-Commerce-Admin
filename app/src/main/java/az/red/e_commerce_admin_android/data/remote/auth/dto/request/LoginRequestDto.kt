package az.red.e_commerce_admin_android.data.remote.auth.dto.request


//TODO: Naming convention
data class LoginRequestDto(
    val loginOrEmail: String,
    val password: String
)