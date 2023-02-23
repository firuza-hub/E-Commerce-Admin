package az.red.e_commerce_admin_android.data.remote.product.dto.response

data class ProductListItemResponse(
    val __v: Int,
    val _id: String,
    val categories: String,
    val color: String,
    val currentPrice: Double,
    val date: String,
    val description: String,
    val enabled: Boolean,
    val fabric: String,
    val imageUrls: List<String>,
    val itemNo: String,
    val name: String,
    val previousPrice: Double,
    val quantity: Int,
    val size: String
)