package az.red.e_commerce_admin_android.data.remote.category

import az.red.e_commerce_admin_android.data.remote.category.dto.CategoryResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<NetworkResult<CategoryResponse>>
}