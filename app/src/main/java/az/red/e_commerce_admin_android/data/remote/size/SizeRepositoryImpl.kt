package az.red.e_commerce_admin_android.data.remote.size

import az.red.e_commerce_admin_android.data.remote.size.dto.SizeResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.handleApi
import kotlinx.coroutines.flow.Flow

class SizeRepositoryImpl(private val service: SizeService) : SizeRepository {
    override fun get(): Flow<NetworkResult<SizeResponse>> =
        handleApi { service.get() }
}