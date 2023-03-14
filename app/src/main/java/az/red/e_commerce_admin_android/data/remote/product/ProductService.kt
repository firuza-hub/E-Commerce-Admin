package az.red.e_commerce_admin_android.data.remote.product

import az.red.e_commerce_admin_android.data.remote.EndPoints
import az.red.e_commerce_admin_android.data.remote.product.dto.request.ProductSearchRequest
import az.red.e_commerce_admin_android.data.remote.product.dto.request.CreateProductRequest
import az.red.e_commerce_admin_android.data.remote.product.dto.response.CreateProductResponse
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductResponse
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductsListResponse
import retrofit2.Response
import retrofit2.http.*

interface ProductService {
    @GET(EndPoints.PRODUCTS_FILTERED)
    suspend fun getProductsFilteredPaging(
        @QueryMap query: Map<String, String>,
        @Query("startPage") startPage: Int,
        @Query("perPage") perPage: Int
    ): ProductsListResponse

    @GET(EndPoints.PRODUCTS_FILTERED)
    suspend fun getProductsFiltered(
        @QueryMap query: Map<String, String>,
        @Query("startPage") startPage: Int? = null,
        @Query("perPage") perPage: Int? = null
    ): Response<ProductsListResponse>

    @GET(EndPoints.PRODUCT + "/{itemNo}")
    suspend fun getProductById(@Path("itemNo") itemNo: String): Response<ProductResponse>


    @POST(EndPoints.PRODUCT_SEARCH)
    suspend fun getProductSearch(@Body request: ProductSearchRequest): Response<ProductsListResponse>

    @PUT(EndPoints.UPDATE_PRODUCT)
    suspend fun deactivateProduct(
        @Path("id") id : String,
        @Body dto : ProductResponse
    ) : Response<ProductResponse>

    @POST(EndPoints.CREATE_PRODUCT)
    suspend fun createProduct(@Body productData: CreateProductRequest): Response<CreateProductResponse>
}