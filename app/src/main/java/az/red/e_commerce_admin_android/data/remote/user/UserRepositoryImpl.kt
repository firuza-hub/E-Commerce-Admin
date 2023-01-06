package az.red.e_commerce_admin_android.data.remote.user

import az.red.e_commerce_admin_android.data.remote.user.dto.request.UpdatePasswordRequest
import az.red.e_commerce_admin_android.data.remote.user.dto.request.UpdateUserRequest
import az.red.e_commerce_admin_android.data.remote.user.dto.response.PasswordUpdateResponse
import az.red.e_commerce_admin_android.data.remote.user.dto.response.UserResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.handleApi
import kotlinx.coroutines.flow.StateFlow

class UserRepositoryImpl(val service:UserService): UserRepository {
    override suspend fun getCurrentUser(): StateFlow<NetworkResult<UserResponse>> {
        return handleApi{service.getCurrentUser()}
    }

    override suspend fun updateUser(dto:UpdateUserRequest): StateFlow<NetworkResult<UserResponse>> {
        return handleApi{service.updateUser(dto)}
    }

    override suspend fun updatePassword(dto: UpdatePasswordRequest): StateFlow<NetworkResult<PasswordUpdateResponse>> {
        return handleApi{service.updatePassword(dto)}
    }
}