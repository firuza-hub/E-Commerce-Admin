package az.red.e_commerce_admin_android.data.remote.brand

import az.red.e_commerce_admin_android.data.remote.brand.dto.BrandResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface BrandRepository {
    fun getBrands(): Flow<NetworkResult<BrandResponse>>
}