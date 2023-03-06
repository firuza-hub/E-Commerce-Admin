package az.red.e_commerce_admin_android.ui.screens.create_product

import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.create_product.CreateProductRepository
import az.red.e_commerce_admin_android.data.remote.create_product.dto.request.CreateProductRequest
import kotlinx.coroutines.launch
import java.util.*

class CreateProductViewModel(
    private val repository: CreateProductRepository,
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            repository.createProduct(
                CreateProductRequest(
                    "braaaand",
                    "/men",
                    currentPrice = 12.0,
                    imageUrls = emptyList(),
                    myCustomParam = "",
                    name = "SAlam",
                    quantity = 0, date = Date(2023, 10, 1)
                )
            ).collect {
                println(it.data)
            }
        }

    }
}