package az.red.e_commerce_admin_android.data.remote.product

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import az.red.e_commerce_admin_android.data.remote.product.dto.ProductService
import az.red.e_commerce_admin_android.data.remote.product.dto.request.ProductListItemRequest
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductResponse
import retrofit2.HttpException
import java.io.IOException

class ProductListPagingSource(
    private val service: ProductService,
    private val request: ProductListItemRequest
) : PagingSource<Int, ProductResponse>() {

    override fun getRefreshKey(state: PagingState<Int, ProductResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductResponse> {
        val position = params.key ?: PRODUCT_LIST_STARTING_PAGE_INDEX
        Log.e("PRODUCT_LIST", "Start Load " + position)
        return try {
            val response = service.getProductsFilteredPaging(request.toMap(), position, params.loadSize)

            Log.e("PRODUCT_LIST", "LOADED " + response.products.size)
            val products = response.products
            val nextKey = if (products.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = products,
                prevKey = if (position == PRODUCT_LIST_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {

            Log.e("PRODUCT_LIST", exception.stackTraceToString())
            LoadResult.Error(exception)

        } catch (exception: HttpException) {

            Log.e("PRODUCT_LIST", exception.stackTraceToString())
            LoadResult.Error(exception)
        }
    }

    private val PRODUCT_LIST_STARTING_PAGE_INDEX = 1
}

