package az.red.e_commerce_admin_android.ui.screens.create_product

import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.brand.BrandRepository
import az.red.e_commerce_admin_android.data.remote.category.CategoryRepository
import az.red.e_commerce_admin_android.data.remote.create_product.CreateProductRepository
import az.red.e_commerce_admin_android.data.remote.create_product.dto.request.CreateProductRequest
import az.red.e_commerce_admin_android.utils.NetworkResult
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateProductViewModel(
    private val createProductRepository: CreateProductRepository,
    private val brandRepository: BrandRepository,
    private val categoryRepository: CategoryRepository
) : BaseViewModel() {

    private val _state = MutableStateFlow(CreateProductState())
    val state: StateFlow<CreateProductState> get() = _state.asStateFlow()

    private val _brandData = MutableStateFlow(emptyList<String>())
    val brandData: StateFlow<List<String>> get() = _brandData.asStateFlow()

    private val _categoryData = MutableStateFlow(emptyList<String>())
    val categoryData: StateFlow<List<String>> get() = _categoryData.asStateFlow()

    init {
        viewModelScope.launch {
            val getBrands = async { getAllBrands() }
            val getCategory = async { getAllCategory() }
            getBrands.await()
            getCategory.await()
        }
    }

    fun createProduct(request: CreateProductRequest) = viewModelScope.launch {
        createProductRepository.createProduct(request).collect {
            when (it) {
                is NetworkResult.Empty -> _state.value = _state.value.copy()
                is NetworkResult.Error -> _state.value = CreateProductState(error = it.message!!)
                is NetworkResult.Exception -> _state.value =
                    CreateProductState(error = it.message!!)
                is NetworkResult.Loading -> _state.value = _state.value.copy(isLoading = true)
                is NetworkResult.Success -> _state.value =
                    _state.value.copy(
                        data = it.data!!,
                        isLoading = false
                    )
            }
        }
    }

    private fun getAllBrands() = viewModelScope.launch {
        brandRepository.getBrands().collect {
            when (it) {
                is NetworkResult.Empty -> _state.value = _state.value.copy()
                is NetworkResult.Error -> _state.value = CreateProductState(error = it.message!!)
                is NetworkResult.Exception -> _state.value =
                    CreateProductState(error = it.message!!)
                is NetworkResult.Loading -> _state.value = _state.value.copy(isLoading = true)
                is NetworkResult.Success -> {
                    _state.value = _state.value.copy(isLoading = false)
                    _brandData.value = it.data!!.map { item -> item.name }
                }
            }
        }
    }

    private fun getAllCategory() = viewModelScope.launch {
        categoryRepository.getCategories().collect {
            when (it) {
                is NetworkResult.Empty -> _state.value = _state.value.copy()
                is NetworkResult.Error -> _state.value = CreateProductState(error = it.message!!)
                is NetworkResult.Exception -> _state.value =
                    CreateProductState(error = it.message!!)
                is NetworkResult.Loading -> _state.value = _state.value.copy(isLoading = true)
                is NetworkResult.Success -> {
                    _state.value = _state.value.copy(isLoading = false)
                    _categoryData.value = it.data!!.map { item -> item.name }
                }
            }
        }
    }
}