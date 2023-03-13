package az.red.e_commerce_admin_android.data.remote.category

import az.red.e_commerce_admin_android.data.remote.category.dto.CategoryResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.handleApi
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(private val service: CategoryService) : CategoryRepository {
    override fun getCategories(): Flow<NetworkResult<CategoryResponse>> =
        handleApi { service.getCategories() }
}