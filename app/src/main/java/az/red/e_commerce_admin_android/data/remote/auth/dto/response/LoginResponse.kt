package az.red.e_commerce_admin_android.data.remote.auth.dto.response

data class LoginResponse(
    val success: Boolean?,
    val token: String?,
    val password:String?,
    val loginOrEmail:String?
)