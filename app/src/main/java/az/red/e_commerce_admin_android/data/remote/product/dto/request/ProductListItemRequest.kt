package az.red.e_commerce_admin_android.data.remote.product.dto.request

@Suppress("UNCHECKED_CAST")
data class ProductListItemRequest(
    val categories: String? = null,
    val color: String? = null,
    val currentPrice: Double? = null,
    val description: String? = null,
    val fabric: String? = null,
    val name: String? = null,
    val size: String? = null,
    val userId: String? = null
) {
    fun toMap(): Map<String, String> {
        return mapOf(
            "categories" to categories,
            "color" to color,
            "currentPrice" to currentPrice?.toString(),
            "description" to description,
            "fabric" to fabric,
            "name" to name,
            "userId" to userId,
            "size" to size
        ).filter { !it.value.isNullOrEmpty() } as Map<String, String>
    }
}