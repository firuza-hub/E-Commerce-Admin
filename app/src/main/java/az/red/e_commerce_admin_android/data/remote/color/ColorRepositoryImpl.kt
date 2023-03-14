package az.red.e_commerce_admin_android.data.remote.color

import az.red.e_commerce_admin_android.data.remote.color.dto.ColorResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.handleApi
import kotlinx.coroutines.flow.Flow

class ColorRepositoryImpl(private val service: ColorService) : ColorRepository {
    override fun get(): Flow<NetworkResult<ColorResponse>> =
        handleApi { service.get() }
}