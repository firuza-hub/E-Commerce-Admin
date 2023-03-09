package az.red.e_commerce_admin_android.data.remote.product.dto.response

import az.red.e_commerce_admin_android.ui.screens.product_details.ProductDetailsState
import az.red.e_commerce_admin_android.ui.screens.product_details.SimilarProduct

data class ProductResponse(
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
){
    fun toProductState():ProductDetailsState{
        return ProductDetailsState(_id,  categories, color, currentPrice, date, description, enabled, fabric, imageUrls, itemNo, name, previousPrice, quantity, size, null)
    }

    fun toSimilarProduct():SimilarProduct{
        return SimilarProduct(_id,  categories, color, currentPrice, date, description.take(15), enabled, fabric, imageUrls, itemNo, name, previousPrice, quantity, size)
    }
}