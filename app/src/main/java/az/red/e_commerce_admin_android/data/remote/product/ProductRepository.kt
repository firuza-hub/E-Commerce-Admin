package az.red.e_commerce_admin_android.data.remote.product

import androidx.paging.PagingData
import az.red.e_commerce_admin_android.data.remote.product.dto.request.CreateProductRequest
import az.red.e_commerce_admin_android.data.remote.product.dto.response.CreateProductResponse
import az.red.e_commerce_admin_android.data.remote.product.dto.request.ProductListItemRequest
import az.red.e_commerce_admin_android.data.remote.product.dto.request.ProductSearchRequest
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductResponse
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductsListResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProductsFilteredPaging(request: ProductListItemRequest): Flow<PagingData<ProductResponse>>
    fun getProductsFiltered(request: ProductListItemRequest, count:Int? = null): Flow<NetworkResult<ProductsListResponse>>
    fun getProductById(id:String): Flow<NetworkResult<ProductResponse>>
    fun getProductSearch(request: ProductSearchRequest): Flow<NetworkResult<ProductsListResponse>>
    fun deactivateProduct(dto : ProductResponse): Flow<NetworkResult<ProductResponse>>
    fun createProduct(productData: CreateProductRequest): Flow<NetworkResult<CreateProductResponse>>
}