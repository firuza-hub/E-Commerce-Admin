package az.red.e_commerce_admin_android.data.remote.review

import az.red.e_commerce_admin_android.data.remote.review.dto.response.ReviewResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    fun getCommentsOfProduct(productID : String): Flow<NetworkResult<List<ReviewResponse>>>
}