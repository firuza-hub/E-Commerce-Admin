package az.red.e_commerce_admin_android.data.remote.brand

import az.red.e_commerce_admin_android.data.remote.EndPoints
import az.red.e_commerce_admin_android.data.remote.brand.dto.BrandResponse
import retrofit2.Response
import retrofit2.http.GET

interface BrandService {
    @GET(EndPoints.BRAND)
    suspend fun getBrands(): Response<BrandResponse>
}