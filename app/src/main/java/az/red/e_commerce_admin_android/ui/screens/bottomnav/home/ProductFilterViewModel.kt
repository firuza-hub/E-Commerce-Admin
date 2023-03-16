package az.red.e_commerce_admin_android.ui.screens.bottomnav.home

import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.brand.BrandRepository
import az.red.e_commerce_admin_android.data.remote.category.CategoryRepository
import az.red.e_commerce_admin_android.data.remote.color.ColorRepository
import az.red.e_commerce_admin_android.data.remote.product.dto.request.ProductListItemRequest
import az.red.e_commerce_admin_android.data.remote.size.SizeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductFilterViewModel(
    private val sizeRepo: SizeRepository,
    private val categoryRepo: CategoryRepository,
    private val brandRepo: BrandRepository,
    private val colorRepo: ColorRepository,
) : BaseViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val _selectedFilterOptions = MutableStateFlow(emptyList<FilterOption>())
    val selectedFilterOptions = _selectedFilterOptions.asStateFlow()

    private val _selectedFilter = MutableStateFlow(ProductFilter.Color)
    val selectedFilter = _selectedFilter.asStateFlow()

    private val _request =
        MutableStateFlow(ProductListItemRequest(userId = sessionManager.fetchUserId()))
    val request = _request.asStateFlow()

    init {
        fetchSelectedFilterOptions()
    }

    private fun fetchSelectedFilterOptions() {
        viewModelScope.launch {
            _isLoading.value = true
            when (_selectedFilter.value) {
                ProductFilter.Color -> colorRepo.get().collect { result ->
                    result.handleResult(onSuccess = {
                        _selectedFilterOptions.value =
                            it.map { i -> i.toFilterOption(_request.value.color) }
                    }, _isLoading, _error, "FETCH_FILTER_OPTIONS")
                }
                ProductFilter.Category -> {
                    categoryRepo.getCategories().collect { result ->
                        result.handleResult(onSuccess = {
                            _selectedFilterOptions.value =
                                it.map { i -> i.toFilterOption(_request.value.categories) }
                        }, _isLoading, _error, "FETCH_FILTER_OPTIONS")
                    }
                }
                ProductFilter.Brand -> brandRepo.getBrands().collect { result ->
                    result.handleResult(onSuccess = {
                        _selectedFilterOptions.value =
                            it.map { i -> i.toFilterOption(_request.value.brand) }
                    }, _isLoading, _error, "FETCH_FILTER_OPTIONS")
                }
                ProductFilter.Size -> sizeRepo.get().collect { result ->
                    result.handleResult(onSuccess = {
                        _selectedFilterOptions.value =
                            it.map { i -> i.toFilterOption(_request.value.size) }
                    }, _isLoading, _error, "FETCH_FILTER_OPTIONS")
                }
            }
        }
    }

    fun onProductFilterValueChange(newValue: ProductFilter) {
        _selectedFilter.value = newValue
        fetchSelectedFilterOptions()
    }

    fun onFilterOptionSelected(it: FilterOption) {
        _request.value = when (_selectedFilter.value) {

            ProductFilter.Color -> {
                if (_request.value.color == it.name) _request.value.copy(color = null) else _request.value.copy(
                    color = it.name
                )
            }
            ProductFilter.Category -> {
                if (_request.value.categories == it.name) _request.value.copy(categories = null) else _request.value.copy(
                    categories = it.name
                )
            }
            ProductFilter.Brand -> {
                if (_request.value.brand == it.name) _request.value.copy(brand = null) else _request.value.copy(
                    brand = it.name
                )
            }
            ProductFilter.Size -> {
                if (_request.value.size == it.name) _request.value.copy(size = null) else _request.value.copy(
                    size = it.name
                )
            }
        }
        fetchSelectedFilterOptions()
    }

    fun clearFilter() {
        _request.value = ProductListItemRequest(userId = sessionManager.fetchUserId())
        fetchSelectedFilterOptions()
    }
}


enum class ProductFilter {
    Color,
    Category,
    Brand,
    Size
}

data class FilterOption(val name: String, val id: String, var isSelected: Boolean = false)
