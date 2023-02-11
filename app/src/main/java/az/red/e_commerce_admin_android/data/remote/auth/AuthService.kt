package az.red.e_commerce_admin_android.data.remote.auth

import az.red.e_commerce_admin_android.data.remote.EndPoints
import az.red.e_commerce_admin_android.data.remote.auth.dto.request.LoginRequest
import az.red.e_commerce_admin_android.data.remote.auth.dto.request.RegisterRequest
import az.red.e_commerce_admin_android.data.remote.auth.dto.response.LoginResponse
import az.red.e_commerce_admin_android.data.remote.auth.dto.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST(EndPoints.LOGIN)
    suspend fun login(@Body loginData: LoginRequest): Response<LoginResponse>

    @POST(EndPoints.REGISTER)
    suspend fun register(@Body registerData: RegisterRequest): Response<RegisterResponse>
}