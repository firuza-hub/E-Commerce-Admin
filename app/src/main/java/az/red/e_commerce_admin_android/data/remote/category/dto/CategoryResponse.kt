package az.red.e_commerce_admin_android.data.remote.category.dto

class CategoryResponse : ArrayList<CategoryResponseItem>()

data class CategoryResponseItem(
    val date: String,
    val description: String,
    val id: String,
    val imgUrl: String,
    val level: Int,
    val name: String,
)