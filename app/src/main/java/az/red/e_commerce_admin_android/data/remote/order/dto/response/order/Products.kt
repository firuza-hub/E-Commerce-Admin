package az.red.e_commerce_admin_android.data.remote.order.dto.response.order

import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("_id")
    val id: String,
    val product : Product,
    val cartQuantity : Int
)
