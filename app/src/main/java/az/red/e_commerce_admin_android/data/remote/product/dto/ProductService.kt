package az.red.e_commerce_admin_android.data.remote.product.dto

import az.red.e_commerce_admin_android.data.remote.EndPoints
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductListItemResponse
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ProductService {
    @GET(EndPoints.PRODUCTS_FILTERED)
    suspend fun getProductsFiltered(@QueryMap query:Map<String, String>,
                                    @Query("startPage") startPage: Int,
                                    @Query("perPage") perPage: Int ): ProductsListResponse
}