package az.red.e_commerce_admin_android.ui.screens.create_product

import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.create_product.CreateProductRepository
import az.red.e_commerce_admin_android.data.remote.create_product.dto.request.CreateProductRequest
import az.red.e_commerce_admin_android.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateProductViewModel(
    private val repository: CreateProductRepository,
) : BaseViewModel() {

    private val _state = MutableStateFlow(CreateProductState())
    val state: StateFlow<CreateProductState> get() = _state.asStateFlow()

    fun createProduct(request: CreateProductRequest) = viewModelScope.launch {
        repository.createProduct(request).collect {
            when (it) {
                is NetworkResult.Empty -> _state.value = CreateProductState()
                is NetworkResult.Error -> _state.value = CreateProductState(error = it.message!!)
                is NetworkResult.Exception -> _state.value =
                    CreateProductState(error = it.message!!)
                is NetworkResult.Loading -> _state.value = CreateProductState(isLoading = true)
                is NetworkResult.Success -> _state.value = CreateProductState(data = it.data!!)
            }
        }
    }
}