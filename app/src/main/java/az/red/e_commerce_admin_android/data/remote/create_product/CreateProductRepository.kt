package az.red.e_commerce_admin_android.data.remote.create_product

import az.red.e_commerce_admin_android.data.remote.create_product.dto.request.CreateProductRequest
import az.red.e_commerce_admin_android.data.remote.create_product.dto.response.CreateProductResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CreateProductRepository {
    fun createProduct(productData: CreateProductRequest): Flow<NetworkResult<CreateProductResponse>>
}