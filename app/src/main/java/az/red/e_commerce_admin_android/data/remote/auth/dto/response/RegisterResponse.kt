package az.red.e_commerce_admin_android.data.remote.auth.dto.response


data class RegisterResponse(
    val isAdmin: Boolean?,
    val enabled: Boolean?,
    val avatarUrl: String?,
    val customerNo: String?,
    val date: String?,
    val email: String?,
    val firstName: String?,
    val gender: String?,
    val lastName: String?,
    val login: String?,
    val password: String?,
    val telephone: String?
)