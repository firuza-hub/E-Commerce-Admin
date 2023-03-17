package az.red.e_commerce_admin_android.di

import az.red.e_commerce_admin_android.ui.screens.login.LoginViewModel
import az.red.e_commerce_admin_android.ui.screens.register.RegisterViewModel
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.ProductListViewModel
import az.red.e_commerce_admin_android.ui.screens.product_details.ProductDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import az.red.e_commerce_admin_android.ui.main.MainViewModel
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.ProfileViewModel
import az.red.e_commerce_admin_android.ui.screens.create_product.CreateProductViewModel
import az.red.e_commerce_admin_android.ui.screens.fill_profile.FillProfileViewModel
import az.red.e_commerce_admin_android.ui.screens.bottomnav.orders.OrderViewModel
import az.red.e_commerce_admin_android.ui.screens.review.ReviewViewModel
import az.red.e_commerce_admin_android.ui.screens.bottomnav.home.ProductFilterViewModel

val presentationModule = module {
    viewModel { LoginViewModel(authRepo = get()) }
    viewModel { MainViewModel() }
    viewModel { RegisterViewModel(authRepo = get()) }
    viewModel { ProfileViewModel(repository = get()) }
    viewModel { ProductListViewModel(repo = get()) }
    viewModel { FillProfileViewModel(repository = get()) }
    viewModel {
        CreateProductViewModel(
            productRepository = get(),
            brandRepository = get(),
            categoryRepository = get()
        )
    }
    viewModel { OrderViewModel(repository = get()) }
    viewModel { ProductDetailsViewModel(repository = get(), get()) }
    viewModel {
        ReviewViewModel(
            productRepository = get(),
            reviewRepository = get(),
            savedStateHandle = get()
        )
    }
    viewModel { ProductFilterViewModel(brandRepo = get(), categoryRepo = get(), colorRepo = get(), sizeRepo = get()) }
}