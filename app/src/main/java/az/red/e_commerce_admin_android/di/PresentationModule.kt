package az.red.e_commerce_admin_android.di

import az.red.e_commerce_admin_android.ui.screens.login.LoginViewModel
import az.red.e_commerce_admin_android.ui.screens.onBoarding.OnBoardingViewModel
import az.red.e_commerce_admin_android.ui.screens.otp.OtpVIewModel
import az.red.e_commerce_admin_android.ui.screens.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { OnBoardingViewModel() }
    viewModel { OtpVIewModel() }
    viewModel { RegisterViewModel(get()) }
}