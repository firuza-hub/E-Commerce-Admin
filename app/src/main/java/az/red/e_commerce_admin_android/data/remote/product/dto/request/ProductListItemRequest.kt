package az.red.e_commerce_admin_android.data.remote.product.dto.request

@Suppress("UNCHECKED_CAST")
data class ProductListItemRequest(
    val categories: String? = null,
    val color: String? = null,
    val fabric: String? = null,
    val name: String? = null,
    val brand: String? = null,
    val size: String? = null
) {
    fun toMap(): Map<String, String> {
        return mapOf(
            "categories" to categories,
            "color" to color,
            "fabric" to fabric,
            "name" to name,
            "size" to size
        ).filter { !it.value.isNullOrEmpty() } as Map<String, String>
    }
}