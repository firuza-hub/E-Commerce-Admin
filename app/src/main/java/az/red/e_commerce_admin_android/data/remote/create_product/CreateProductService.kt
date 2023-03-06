package az.red.e_commerce_admin_android.data.remote.create_product

import az.red.e_commerce_admin_android.data.remote.EndPoints
import az.red.e_commerce_admin_android.data.remote.create_product.dto.request.CreateProductRequest
import az.red.e_commerce_admin_android.data.remote.create_product.dto.response.CreateProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateProductService {
    @POST(EndPoints.CREATE_PRODUCT)
    suspend fun createProduct(@Body productData: CreateProductRequest): Response<CreateProductResponse>
}