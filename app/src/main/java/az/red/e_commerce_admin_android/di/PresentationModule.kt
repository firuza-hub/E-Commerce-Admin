package az.red.e_commerce_admin_android.di

import az.red.e_commerce_admin_android.content.login.LoginViewModel
import az.red.e_commerce_admin_android.content.onBoarding.OnBoardingViewModel
import az.red.e_commerce_admin_android.content.otp.OtpVIewModel
import az.red.e_commerce_admin_android.content.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { OnBoardingViewModel() }
    viewModel { OtpVIewModel() }
    viewModel { RegisterViewModel(get()) }
}