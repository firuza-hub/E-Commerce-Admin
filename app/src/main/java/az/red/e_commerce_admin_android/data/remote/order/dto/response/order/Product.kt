package az.red.e_commerce_admin_android.data.remote.order.dto.response.order

import com.google.gson.annotations.SerializedName

data class Product(
    val enabled : Boolean,
    val imageUrls : List<String>,
    val quantity : Int,
    @SerializedName("_id")
    val id : String,
    val name : String,
    val currentPrice : Double,
    val previousPrice : Double,
    val categories : String,
    val colour : String,
    val fabric : String,
    val description : String,
    val itemNo : String,
    val date : String,
    @SerializedName("__v")
    val v: Int,
    val size : String
)
