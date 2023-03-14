package az.red.e_commerce_admin_android.data.remote.size

import az.red.e_commerce_admin_android.data.remote.EndPoints
import az.red.e_commerce_admin_android.data.remote.size.dto.SizeResponse
import retrofit2.Response
import retrofit2.http.GET

interface SizeService {
    @GET(EndPoints.SIZE)
    suspend fun get(): Response<SizeResponse>
}