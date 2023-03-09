package az.red.e_commerce_admin_android.data.remote.order.dto.response

import az.red.e_commerce_admin_android.data.remote.order.dto.response.order.DeliveryAddress
import az.red.e_commerce_admin_android.data.remote.order.dto.response.order.Products
import az.red.e_commerce_admin_android.data.remote.user.dto.response.UserResponse
import com.google.gson.annotations.SerializedName

data class OrderResponse(
    val products : List<Products>,
    val canceled : Boolean,
    @SerializedName("_id")
    val id : String,
    val customerId : UserResponse,
    val deliveryAddress : DeliveryAddress,
    val shipping : String,
    val paymentInfo : String,
    val status : String,
    val email : String,
    val mobile : String,
    val letterSubject : String,
    val letterHtml : String,
    val orderNo : String,
    val totalSum : Double,
    val date : String,
    @SerializedName("__v")
    val v : Int
)