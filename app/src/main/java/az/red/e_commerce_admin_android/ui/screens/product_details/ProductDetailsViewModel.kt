package az.red.e_commerce_admin_android.ui.screens.product_details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.product.ProductRepository
import az.red.e_commerce_admin_android.data.remote.product.dto.request.ProductListItemRequest
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val repository: ProductRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _state = MutableStateFlow(ProductDetailsState.NULL)
    val state = _state.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _similarProducts = MutableStateFlow(emptyList<SimilarProduct>())
    val similarProducts = _similarProducts.asStateFlow()

    init {
        viewModelScope.launch {
            savedStateHandle.get<String>("itemNo")?.let {
                repository.getProductById(it).collect { result ->
                    result.handleResult(onSuccess = {
                        _state.value = it.toProductState()
                        fetchSimilarProducts()
                    }, "PRODUCT_DETAILS")
                }
            }
        }
    }

    private fun fetchSimilarProducts() {
        viewModelScope.launch {
            repository.getProductsFiltered(
                ProductListItemRequest(categories = state.value.categories),
                count = 5
            ).collect { result ->
                result.handleResult(onSuccess = {
                    _similarProducts.value =
                        it.products.map { i -> i.toSimilarProduct() }
                }, "SIMILAR_PRODUCTS")
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
                _state.value.error = this.message
                Log.i(tag, "Error: ${this.message}")
                triggerEvent(UIEvent.Message("no data"))
            }
            is NetworkResult.Exception -> {
                _isLoading.value = false
                _state.value.error = this.message
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
                    fetchSimilarProducts()
                } else {
                    triggerEvent(UIEvent.Error("no data"))
                    Log.i(tag, "Success -> no data")
                }
            }
        }
    }
}