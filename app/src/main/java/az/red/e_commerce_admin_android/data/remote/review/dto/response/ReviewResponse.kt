package az.red.e_commerce_admin_android.data.remote.review.dto.response

import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductResponse
import az.red.e_commerce_admin_android.data.remote.user.dto.response.UserResponse
import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("_id")
    val id : String,
    val customer : UserResponse,
    val product: ProductResponse,
    val content : String,
    val __v : Int
)
