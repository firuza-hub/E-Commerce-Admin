package az.red.e_commerce_admin_android.ui.screens.review

import az.red.e_commerce_admin_android.data.remote.review.dto.response.ReviewResponse

data class ReviewState (
    val review : List<ReviewResponse>
){
    companion object {
        val NULL = ReviewState(emptyList())
    }
}
