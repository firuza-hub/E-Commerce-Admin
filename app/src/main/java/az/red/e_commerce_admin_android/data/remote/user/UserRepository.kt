package az.red.e_commerce_admin_android.data.remote.user

import az.red.e_commerce_admin_android.data.remote.user.dto.request.UpdatePasswordRequest
import az.red.e_commerce_admin_android.data.remote.user.dto.request.UpdateUserRequest
import az.red.e_commerce_admin_android.data.remote.user.dto.response.PasswordUpdateResponse
import az.red.e_commerce_admin_android.data.remote.user.dto.response.UserResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {
    suspend fun getCurrentUser(): StateFlow<NetworkResult<UserResponse>>
    suspend fun updateUser(dto: UpdateUserRequest): StateFlow<NetworkResult<UserResponse>>
    suspend fun updatePassword(dto: UpdatePasswordRequest): StateFlow<NetworkResult<PasswordUpdateResponse>>
}