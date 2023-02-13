package az.red.e_commerce_admin_android.di

import az.red.e_commerce_admin_android.ui.login.LoginViewModel
import az.red.e_commerce_admin_android.ui.onBoarding.OnBoardingViewModel
import az.red.e_commerce_admin_android.ui.otp.OtpVIewModel
import az.red.e_commerce_admin_android.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { LoginViewModel(get(),get()) }
    viewModel { OnBoardingViewModel() }
    viewModel { OtpVIewModel() }
    viewModel { RegisterViewModel(get()) }
}