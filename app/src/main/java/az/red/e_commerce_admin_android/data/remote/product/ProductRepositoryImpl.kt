package az.red.e_commerce_admin_android.data.remote.product

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import az.red.e_commerce_admin_android.data.remote.product.dto.ProductService
import az.red.e_commerce_admin_android.data.remote.product.dto.request.ProductListItemRequest
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductListItemResponse
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(private val service: ProductService) : ProductRepository {

    override fun getProductsFiltered(request: ProductListItemRequest): Flow<PagingData<ProductListItemResponse>> {
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
}

const val NETWORK_PAGE_SIZE = 6