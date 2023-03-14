package az.red.e_commerce_admin_android.ui.screens.bottomnav.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.product.ProductRepository
import az.red.e_commerce_admin_android.data.remote.product.dto.request.ProductListItemRequest
import az.red.e_commerce_admin_android.data.remote.product.dto.response.ProductResponse
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProductListViewModel(val repo: ProductRepository) : BaseViewModel() {

    var data: Flow<PagingData<ProductResponse>>

    init {
        Log.i("CURRENT_THREAD vm", Thread.currentThread().name)
        data = repo.getProductsFilteredPaging(ProductListItemRequest()).cachedIn(viewModelScope)

    }

    fun deactivateProduct(dto:ProductResponse,isDeactivate:Boolean){
        viewModelScope.launch {
            val productResponse = fillProductResponse(dto, isDeactivate)
            repo.deactivateProduct(productResponse).collect {
                when (it) {
                    is NetworkResult.Success -> {
                        val productStatus = if(!isDeactivate) "deactivated" else "activated"
                        triggerEvent(UIEvent.Message("${dto.name} has been $productStatus!"))
                    }
                    is NetworkResult.Empty -> Log.i("DEACTIVATE_PRODUCT", "Empty")
                    is NetworkResult.Error -> {
                        Log.i("DEACTIVATE_PRODUCT", "Error: ${it.message}")
                        it.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                    }
                    is NetworkResult.Exception -> {
                        Log.e("DEACTIVATE_PRODUCT", "Exception: ${it.message}")
                        it.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                    }
                    is NetworkResult.Loading -> {
                        Log.i("DEACTIVATE_PRODUCT", "Loading")
                    }
                }
            }
        }
    }

    private fun fillProductResponse(dto : ProductResponse,isDeactivate:Boolean):ProductResponse{
        return ProductResponse(
            __v = dto.__v,
            _id = dto._id,
            categories = dto.categories,
            color = dto.color,
            currentPrice = dto.currentPrice,
            date = dto.date,
            description = dto.description,
            enabled = isDeactivate,
            fabric = dto.fabric,
            imageUrls = dto.imageUrls,
            itemNo = dto.itemNo,
            name = dto.name,
            previousPrice = dto.previousPrice,
            quantity = dto.quantity,
            size = dto.size
        )
    }

}
