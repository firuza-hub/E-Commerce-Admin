package az.red.e_commerce_admin_android.ui.screens.product_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.product.ProductRepository
import az.red.e_commerce_admin_android.data.remote.product.dto.request.ProductListItemRequest
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

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val _similarProducts = MutableStateFlow(emptyList<ProductModel>())
    val similarProducts = _similarProducts.asStateFlow()

    init {
        _isLoading.value = true
        viewModelScope.launch {
            savedStateHandle.get<String>("itemNo")?.let {
                repository.getProductById(it).collect { result ->
                    result.handleResult(onSuccess = {
                        _state.value = it.toProductState()
                        fetchSimilarProducts()
                    }, _isLoading, _error, "PRODUCT_DETAILS")
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
                        it.products.map { i -> i.toProductModel() }
                }, _isLoading, _error, "SIMILAR_PRODUCTS")
            }
        }
    }
}