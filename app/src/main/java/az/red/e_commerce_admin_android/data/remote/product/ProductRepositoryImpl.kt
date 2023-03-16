package az.red.e_commerce_admin_android.data.remote.product

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import az.red.e_commerce_admin_android.data.remote.product.dto.request.CreateProductRequest
import az.red.e_commerce_admin_android.data.remote.product.dto.request.ProductListItemRequest
import az.red.e_commerce_admin_android.data.remote.product.dto.request.ProductSearchRequest
import az.red.e_commerce_admin_android.data.remote.product.dto.response.CreateProductResponse
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductResponse
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductsListResponse
import az.red.e_commerce_admin_android.ui.screens.product_details.ProductModel
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.handleApi
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(private val service: ProductService) : ProductRepository {

    override fun getProductsFilteredPaging(request: ProductListItemRequest): Flow<PagingData<ProductModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ProductListPagingSource(service = service, request)
            }
        ).flow
    }

    override fun getProductsFiltered(
        request: ProductListItemRequest,
        count: Int?
    ): Flow<NetworkResult<ProductsListResponse>> {
        return handleApi {
            count?.let {
                service.getProductsFiltered(
                    query = request.toMap(),
                    startPage = 1,
                    perPage = it
                )
            } ?: service.getProductsFiltered(
                query = request.toMap()
            )
        }
    }

    override fun getProductById(id: String): Flow<NetworkResult<ProductResponse>> {
        return handleApi { service.getProductById(id) }
    }

    override fun deactivateProduct(
        dto: ProductResponse
    ): Flow<NetworkResult<ProductResponse>> {
        return handleApi { service.deactivateProduct(dto._id, dto) }
    }

    override fun createProduct(productData: CreateProductRequest): Flow<NetworkResult<CreateProductResponse>> =
        handleApi { service.createProduct(productData) }

    override fun getProductSearch(request: ProductSearchRequest): Flow<NetworkResult<ProductsListResponse>> {
        return handleApi { service.getProductSearch(request) }
    }
}

const val NETWORK_PAGE_SIZE = 6