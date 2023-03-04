package az.red.e_commerce_admin_android.data.remote.user

import az.red.e_commerce_admin_android.data.remote.user.dto.request.FillProfileRequest
import az.red.e_commerce_admin_android.data.remote.user.dto.request.UpdatePasswordRequest
import az.red.e_commerce_admin_android.data.remote.user.dto.request.UpdateUserRequest
import az.red.e_commerce_admin_android.data.remote.user.dto.response.PasswordUpdateResponse
import az.red.e_commerce_admin_android.data.remote.user.dto.response.UserResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getCurrentUser(): Flow<NetworkResult<UserResponse>>
    fun updateUser(dto: UpdateUserRequest): Flow<NetworkResult<UserResponse>>
    fun updatePassword(dto: UpdatePasswordRequest): Flow<NetworkResult<PasswordUpdateResponse>>
    fun fillProfile(dto: FillProfileRequest): Flow<NetworkResult<UserResponse>>
}

