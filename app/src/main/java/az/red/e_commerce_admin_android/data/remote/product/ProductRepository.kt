package az.red.e_commerce_admin_android.data.remote.product

import androidx.paging.PagingData
import az.red.e_commerce_admin_android.data.remote.product.dto.request.ProductListItemRequest
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductListItemResponse
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductsListResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProductsFiltered(request: ProductListItemRequest): Flow<PagingData<ProductListItemResponse>>
}