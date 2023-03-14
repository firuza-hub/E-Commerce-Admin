package az.red.e_commerce_admin_android.data.remote.color

import az.red.e_commerce_admin_android.data.remote.color.dto.ColorResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ColorRepository {
    fun get(): Flow<NetworkResult<ColorResponse>>
}