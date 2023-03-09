package az.red.e_commerce_admin_android.ui.screens.create_product

import az.red.e_commerce_admin_android.data.remote.create_product.dto.response.CreateProductResponse

data class CreateProductState(
    val data: CreateProductResponse? = null,
    val error: String = "",
    val isLoading: Boolean = false
)