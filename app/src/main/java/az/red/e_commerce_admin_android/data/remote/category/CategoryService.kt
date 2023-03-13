package az.red.e_commerce_admin_android.data.remote.category

import az.red.e_commerce_admin_android.data.remote.EndPoints
import az.red.e_commerce_admin_android.data.remote.category.dto.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CategoryService {
    @GET(EndPoints.CATEGORY)
    suspend fun getCategories(): Response<CategoryResponse>
}