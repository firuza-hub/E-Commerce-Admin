package az.red.e_commerce_admin_android.ui.screens.select_item

import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.brand.BrandRepository
import az.red.e_commerce_admin_android.data.remote.category.CategoryRepository

class SelectItemViewModel(
    private val brandRepository: BrandRepository,
    private val categoryRepository: CategoryRepository
) : BaseViewModel() {
}