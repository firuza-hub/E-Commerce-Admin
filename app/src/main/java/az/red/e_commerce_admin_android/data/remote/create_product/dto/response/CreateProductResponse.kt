package az.red.e_commerce_admin_android.data.remote.create_product.dto.response
data class CreateProductResponse(
    val __v: Int,
    val _id: String,
    val brand: String,
    val categories: String,
    val color: String,
    val currentPrice: Double,
    val date: String,
    val enabled: Boolean,
    val imageUrls: List<String>,
    val itemNo: String,
    val myCustomParam: String,
    val name: String,
    val previousPrice: Int,
    val productUrl: String,
    val quantity: Int
)
