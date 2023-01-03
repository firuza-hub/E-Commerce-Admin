package az.red.e_commerce_admin_android.data.remote.auth

import az.red.e_commerce_admin_android.data.remote.auth.dto.request.LoginRequestDto
import az.red.e_commerce_admin_android.data.remote.auth.dto.request.RegisterRequestDto
import az.red.e_commerce_admin_android.data.remote.auth.dto.response.LoginResponseDto
import az.red.e_commerce_admin_android.data.remote.auth.dto.response.RegisterResponseDto
import retrofit2.Response
import retrofit2.http.POST

interface AuthService {

    @POST("/customers/login")
    suspend fun login(loginData: LoginRequestDto): Response<LoginResponseDto>

    @POST("/customers")
    suspend fun register(registerData: RegisterRequestDto): Response<RegisterResponseDto>
}