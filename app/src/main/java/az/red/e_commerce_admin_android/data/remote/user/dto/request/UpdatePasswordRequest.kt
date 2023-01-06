package az.red.e_commerce_admin_android.data.remote.user.dto.request

data class UpdatePasswordRequest(
    val password: String,
    val newPassword: String
)