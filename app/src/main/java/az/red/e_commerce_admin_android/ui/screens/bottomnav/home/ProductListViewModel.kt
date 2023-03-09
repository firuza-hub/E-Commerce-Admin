package az.red.e_commerce_admin_android.ui.screens.bottomnav.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.product.ProductRepository
import az.red.e_commerce_admin_android.data.remote.product.dto.request.ProductListItemRequest
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductResponse
import kotlinx.coroutines.flow.Flow

class ProductListViewModel(repo: ProductRepository) : BaseViewModel() {

    var data: Flow<PagingData<ProductResponse>>

    init {
        Log.i("CURRENT_THREAD vm", Thread.currentThread().name)
        data = repo.getProductsFilteredPaging(ProductListItemRequest()).cachedIn(viewModelScope)

    }

}
