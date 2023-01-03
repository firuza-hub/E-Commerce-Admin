package az.red.e_commerce_admin_android.data.remote.auth

import az.red.e_commerce_admin_android.data.remote.auth.dto.request.LoginRequest
import az.red.e_commerce_admin_android.data.remote.auth.dto.request.RegisterRequest
import az.red.e_commerce_admin_android.data.remote.auth.dto.response.LoginResponse
import az.red.e_commerce_admin_android.data.remote.auth.dto.response.RegisterResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    suspend fun login(loginData: LoginRequest): StateFlow<NetworkResult<LoginResponse>>
    suspend fun register(registerData: RegisterRequest): StateFlow<NetworkResult<RegisterResponse>>
}