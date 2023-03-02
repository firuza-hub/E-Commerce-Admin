package az.red.e_commerce_admin_android.data.remote.user

import az.red.e_commerce_admin_android.data.remote.user.dto.request.FillProfileRequest
import az.red.e_commerce_admin_android.data.remote.user.dto.request.UpdatePasswordRequest
import az.red.e_commerce_admin_android.data.remote.user.dto.request.UpdateUserRequest
import az.red.e_commerce_admin_android.data.remote.user.dto.response.PasswordUpdateResponse
import az.red.e_commerce_admin_android.data.remote.user.dto.response.UserResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.handleApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class UserRepositoryImpl(val service:UserService): UserRepository {
    override  fun getCurrentUser(): Flow<NetworkResult<UserResponse>> {
        return handleApi{service.getCurrentUser()}
    }

    override  fun updateUser(dto:UpdateUserRequest): Flow<NetworkResult<UserResponse>> {
        return handleApi{service.updateUser(dto)}
    }

    override  fun updatePassword(dto: UpdatePasswordRequest): Flow<NetworkResult<PasswordUpdateResponse>> {
        return handleApi{service.updatePassword(dto)}
    }

    override fun fillProfile(dto: FillProfileRequest): Flow<NetworkResult<UserResponse>> {
        return handleApi { service.fillProfile(dto) }
    }
}