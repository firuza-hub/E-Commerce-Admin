package az.red.e_commerce_admin_android.ui.screens.create_product

import android.net.Uri
import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.brand.BrandRepository
import az.red.e_commerce_admin_android.data.remote.category.CategoryRepository
import az.red.e_commerce_admin_android.data.remote.product.ProductRepository
import az.red.e_commerce_admin_android.data.remote.product.dto.request.CreateProductRequest
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.UIEvent
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*

class CreateProductViewModel(
    private val productRepository: ProductRepository,
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

    private fun createProduct(request: CreateProductRequest) = viewModelScope.launch {
        productRepository.createProduct(request).collect {
            when (it) {
                is NetworkResult.Empty -> _state.value = _state.value.copy()
                is NetworkResult.Error -> _state.value = CreateProductState(error = it.message!!)
                is NetworkResult.Exception -> _state.value =
                    CreateProductState(error = it.message!!)
                is NetworkResult.Loading -> _state.value = _state.value.copy(isLoading = true)
                is NetworkResult.Success -> _state.value =
                    _state.value.copy(
                        data = it.data!!,
                        isLoading = false,
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

    fun createProduct(
        brand: String,
        categories: String,
        description: String,
        currentPrice: Double,
        name: String,
        imagesPath: List<Uri>
    ) {
        val storageRef = Firebase.storage.reference
        val list = arrayListOf<String>()
        for (i in imagesPath) {
            val uuid = UUID.randomUUID()
            val mountainImagesRef = storageRef.child("images/${uuid}.jpg")
            val uploadTask = mountainImagesRef.putFile(i)

            uploadTask.addOnSuccessListener { taskSnapshot ->
                if (taskSnapshot.metadata != null) {
                    if (taskSnapshot.metadata!!.reference != null) {
                        val result: Task<Uri> = taskSnapshot.storage.downloadUrl
                        result.addOnSuccessListener { uri ->
                            val imageUrl = uri.toString()
                            list.add(imageUrl)
                            _state.value = _state.value.copy(isLoading = false)

                            if (list.size == imagesPath.size) {
                                createProduct(
                                    CreateProductRequest(
                                        brand = brand,
                                        categories = categories,
                                        imageUrls = list,
                                        description = description,
                                        currentPrice = currentPrice,
                                        name = name,
                                        quantity = 1000,//so that we can place an order on this product
                                        enabled = true,
                                        date = Calendar.getInstance().time,
                                        userId = sessionManager.fetchUserId()
                                    )
                                )
                                triggerEvent(UIEvent.Navigate(Graph.MAIN))
                            }
                        }
                    }
                }
            }.addOnFailureListener {
                _state.value = _state.value.copy(isLoading = false)
            }.addOnProgressListener {
                _state.value = _state.value.copy(isLoading = true)
            }
        }
    }
}