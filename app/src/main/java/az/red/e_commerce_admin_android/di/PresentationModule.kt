package az.red.e_commerce_admin_android.di

import az.red.e_commerce_admin_android.ui.screens.login.LoginViewModel
import az.red.e_commerce_admin_android.ui.screens.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import az.red.e_commerce_admin_android.ui.main.MainViewModel
import az.red.e_commerce_admin_android.ui.screens.bottomnav.profile.ProfileViewModel

val presentationModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel() }
    viewModel { RegisterViewModel(get()) }
    viewModel { ProfileViewModel() }
}