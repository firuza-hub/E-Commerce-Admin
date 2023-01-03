package az.red.e_commerce_admin_android.data.remote.auth

import az.red.e_commerce_admin_android.data.remote.auth.dto.request.LoginRequest
import az.red.e_commerce_admin_android.data.remote.auth.dto.request.RegisterRequest
import az.red.e_commerce_admin_android.data.remote.auth.dto.response.LoginResponse
import az.red.e_commerce_admin_android.data.remote.auth.dto.response.RegisterResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.handleApi
import kotlinx.coroutines.flow.StateFlow

class AuthRepositoryImpl(private val service: AuthService) : AuthRepository {
    override suspend fun login(loginData: LoginRequest): StateFlow<NetworkResult<LoginResponse>> =
        handleApi { service.login(loginData) }

    override suspend fun register(registerData: RegisterRequest): StateFlow<NetworkResult<RegisterResponse>> =
        handleApi { service.register(registerData) }
}
