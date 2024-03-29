package az.red.e_commerce_admin_android.data.remote.product.dto.request

import java.util.Date

data class CreateProductRequest(
    val brand: String,
    val categories: String,
    val currentPrice: Double,
    val imageUrls: List<String>,
    val name: String,
    val description: String,
    val quantity: Int,
    val enabled:Boolean = true,
    val date: Date,
    val userId: String?
)