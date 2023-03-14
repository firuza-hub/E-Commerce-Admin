package az.red.e_commerce_admin_android.data.remote.review

import az.red.e_commerce_admin_android.data.remote.EndPoints
import az.red.e_commerce_admin_android.data.remote.review.dto.response.ReviewResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ReviewService {

    @GET(EndPoints.GET_COMMENTS_OF_CUSTOMER)
    suspend fun getCommentsOfCustomer(
        @Path("productId") productID: String
    ): Response<List<ReviewResponse>>
}