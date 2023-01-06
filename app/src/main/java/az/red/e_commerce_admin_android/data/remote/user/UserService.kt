package az.red.e_commerce_admin_android.data.remote.user

import az.red.e_commerce_admin_android.data.remote.user.dto.request.UpdatePasswordRequest
import az.red.e_commerce_admin_android.data.remote.user.dto.request.UpdateUserRequest
import az.red.e_commerce_admin_android.data.remote.user.dto.response.PasswordUpdateResponse
import az.red.e_commerce_admin_android.data.remote.user.dto.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserService  {
    @GET("/customers/customer")
    suspend fun getCurrentUser(): Response<UserResponse>

    @PUT("/customers")
    suspend fun updateUser(dto: UpdateUserRequest): Response<UserResponse>

    @PUT("/customers/password")
    suspend fun updatePassword(dto: UpdatePasswordRequest): Response<PasswordUpdateResponse>
}