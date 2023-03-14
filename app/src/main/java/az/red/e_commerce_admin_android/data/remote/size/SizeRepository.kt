package az.red.e_commerce_admin_android.data.remote.size

import az.red.e_commerce_admin_android.data.remote.size.dto.SizeResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface SizeRepository {
    fun get(): Flow<NetworkResult<SizeResponse>>
}