package az.red.e_commerce_admin_android.data.remote.color

import az.red.e_commerce_admin_android.data.remote.EndPoints
import az.red.e_commerce_admin_android.data.remote.color.dto.ColorResponse
import retrofit2.Response
import retrofit2.http.GET

interface ColorService {
    @GET(EndPoints.COLOR)
    suspend fun get(): Response<ColorResponse>
}