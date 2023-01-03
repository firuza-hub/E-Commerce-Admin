package az.red.e_commerce_admin_android.data.remote.auth

import az.red.e_commerce_admin_android.data.remote.auth.dto.request.LoginRequestDto
import az.red.e_commerce_admin_android.data.remote.auth.dto.request.RegisterRequestDto
import az.red.e_commerce_admin_android.data.remote.auth.dto.response.LoginResponseDto
import az.red.e_commerce_admin_android.data.remote.auth.dto.response.RegisterResponseDto
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.handleApi

class AuthRepository(private val service: AuthService) {

    suspend fun login(loginData: LoginRequestDto): NetworkResult<LoginResponseDto> =
        handleApi { service.login(loginData) }

    suspend fun register(registerData: RegisterRequestDto): NetworkResult<RegisterResponseDto> =
        handleApi { service.register(registerData) }
}
