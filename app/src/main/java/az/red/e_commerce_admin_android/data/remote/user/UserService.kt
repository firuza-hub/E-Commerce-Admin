package az.red.e_commerce_admin_android.data.remote.user

import az.red.e_commerce_admin_android.data.remote.EndPoints
import az.red.e_commerce_admin_android.data.remote.user.dto.request.FillProfileRequest
import az.red.e_commerce_admin_android.data.remote.user.dto.request.UpdatePasswordRequest
import az.red.e_commerce_admin_android.data.remote.user.dto.request.UpdateUserRequest
import az.red.e_commerce_admin_android.data.remote.user.dto.response.PasswordUpdateResponse
import az.red.e_commerce_admin_android.data.remote.user.dto.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserService {
    @GET(EndPoints.CURRENT_USER)
    suspend fun getCurrentUser(): Response<UserResponse>

    @PUT(EndPoints.UPDATE_USER)
    suspend fun updateUser(dto: UpdateUserRequest): Response<UserResponse>

    @PUT(EndPoints.UPDATE_USER_PASSWORD)
    suspend fun updatePassword(dto: UpdatePasswordRequest): Response<PasswordUpdateResponse>

    @PUT(EndPoints.UPDATE_USER)
    suspend fun fillProfile(@Body dto: FillProfileRequest): Response<UserResponse>

}