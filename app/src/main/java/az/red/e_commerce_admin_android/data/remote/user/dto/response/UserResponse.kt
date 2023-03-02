package az.red.e_commerce_admin_android.data.remote.user.dto.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("_id")
    val id: String,
    val isAdmin: Boolean,
    val enabled: Boolean,
    val avatarUrl: String,
    val customerNo: String,
    val date: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val login: String,
    val password: String,
    val telephone: String,
    val birthdate: String,
    @SerializedName("__v")
    val v: Int
)