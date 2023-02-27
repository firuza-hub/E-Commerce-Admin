package az.red.e_commerce_admin_android.ui.screens.bottomnav.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.product.ProductRepository
import az.red.e_commerce_admin_android.data.remote.product.dto.request.ProductListItemRequest
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductListItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ProductListViewModel(private val repo: ProductRepository) : BaseViewModel() {

    lateinit var data: Flow<PagingData<ProductListItemResponse>>

    init {
            Log.i("CURRENT_THREAD vm", Thread.currentThread().name)
            data = repo.getProductsFiltered(ProductListItemRequest()).cachedIn(viewModelScope)

    }


}
