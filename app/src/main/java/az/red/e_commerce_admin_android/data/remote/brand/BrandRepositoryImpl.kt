package az.red.e_commerce_admin_android.data.remote.brand

import az.red.e_commerce_admin_android.data.remote.brand.dto.BrandResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.handleApi
import kotlinx.coroutines.flow.Flow

class BrandRepositoryImpl(private val service: BrandService) : BrandRepository {
    override fun getBrands(): Flow<NetworkResult<BrandResponse>> = handleApi { service.getBrands() }
}