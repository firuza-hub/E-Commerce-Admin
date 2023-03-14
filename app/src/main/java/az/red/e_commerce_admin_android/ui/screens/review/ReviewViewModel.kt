package az.red.e_commerce_admin_android.ui.screens.review

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.product.ProductRepository
import az.red.e_commerce_admin_android.data.remote.review.ReviewRepository
import az.red.e_commerce_admin_android.ui.screens.product_details.ProductDetailsState
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReviewViewModel(
    private val productRepository: ProductRepository,
    private val reviewRepository: ReviewRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _productDetailState = MutableStateFlow(ProductDetailsState.NULL)
    val productDetailState = _productDetailState.asStateFlow()

    private val _review = MutableStateFlow(ReviewState.NULL)
    val review = _review.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            savedStateHandle.get<String>("itemNo")?.let { productId ->
                productRepository.getProductById(productId).collect { result ->

                    result.handleResult(
                        onSuccess = { productResponse ->
                            _productDetailState.value = productResponse.toProductState()
                            getCommentsOfCustomer(result.data!!._id)
                        }, "PRODUCT_REVIEW"
                    )
                }
            }
        }
    }

    private fun getCommentsOfCustomer(productId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            reviewRepository.getCommentsOfProduct(productId).collect { networkResult ->
                when (networkResult) {
                    is NetworkResult.Success -> {
                        _isLoading.value = false
                        triggerEvent(UIEvent.Message("Success"))
                        _review.value = review.value.copy(
                            review = networkResult.data!!
                        )
                    }
                    is NetworkResult.Empty -> {
                        _isLoading.value = false
                        Log.i("PRODUCT_REVIEW", "Empty")
                    }
                    is NetworkResult.Error -> {
                        _isLoading.value = false
                        Log.i("PRODUCT_REVIEW", "Error: ${networkResult.message}")
                        networkResult.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                    }
                    is NetworkResult.Exception -> {
                        _isLoading.value = false
                        Log.e("PRODUCT_REVIEW", "Exception: ${networkResult.message}")
                        networkResult.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                    }
                    is NetworkResult.Loading -> {
                        _isLoading.value = true
                        Log.i("PRODUCT_REVIEW", "Loading")
                    }
                }
            }
        }
    }

    private fun <T : Any> NetworkResult<T>.handleResult(
        onSuccess: (data: T) -> Unit,
        tag: String? = null
    ) {
        when (this) {
            is NetworkResult.Empty -> {
                _isLoading.value = false
                Log.i(tag, "Empty")
                triggerEvent(UIEvent.Error("no data"))
            }
            is NetworkResult.Error -> {
                _isLoading.value = false
                _productDetailState.value.error = this.message
                Log.i(tag, "Error: ${this.message}")
                triggerEvent(UIEvent.Message("no data"))
            }
            is NetworkResult.Exception -> {
                _isLoading.value = false
                _productDetailState.value.error = this.message
                Log.i(tag, "Exception: ${this.message}")
                triggerEvent(UIEvent.Error("no data"))
            }
            is NetworkResult.Loading -> {
                _isLoading.value = true
                Log.i(tag, "Loading")
            }
            is NetworkResult.Success -> {
                _isLoading.value = false
                if (this.data != null) {
                    onSuccess(this.data)
                    Log.i(tag, "Success")
                } else {
                    triggerEvent(UIEvent.Error("no data"))
                    Log.i(tag, "Success -> no data")
                }
            }
        }
    }

}