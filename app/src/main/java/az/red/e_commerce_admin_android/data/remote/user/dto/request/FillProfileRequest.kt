package az.red.e_commerce_admin_android.data.remote.user.dto.request


data class FillProfileRequest(
    val fullName: String,
    val nickname: String,
    val email: String,
    val birthdate: String,
    val telephone: String,
    val gender: String,
    val avatarUrl: String,
)
