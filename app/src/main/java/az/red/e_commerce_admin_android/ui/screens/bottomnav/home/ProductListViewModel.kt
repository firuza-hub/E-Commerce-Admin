package az.red.e_commerce_admin_android.ui.screens.bottomnav.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.product.ProductRepository
import az.red.e_commerce_admin_android.data.remote.product.dto.request.ProductListItemRequest
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductListItemResponse
import kotlinx.coroutines.flow.Flow

class ProductListViewModel(private val repo: ProductRepository) : BaseViewModel() {

    init {
        getList()
    }

    fun getList(): Flow<PagingData<ProductListItemResponse>> =
        repo.getProductsFiltered(ProductListItemRequest()).cachedIn(viewModelScope)

}
