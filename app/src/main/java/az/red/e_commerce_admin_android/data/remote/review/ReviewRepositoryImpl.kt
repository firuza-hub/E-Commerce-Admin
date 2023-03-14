package az.red.e_commerce_admin_android.data.remote.review

import az.red.e_commerce_admin_android.data.remote.review.dto.response.ReviewResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.handleApi
import kotlinx.coroutines.flow.Flow

class ReviewRepositoryImpl(
    private val service: ReviewService
) : ReviewRepository {
    override fun getCommentsOfProduct(productID : String): Flow<NetworkResult<List<ReviewResponse>>> {
        return handleApi { service.getCommentsOfCustomer(productID) }
    }
}