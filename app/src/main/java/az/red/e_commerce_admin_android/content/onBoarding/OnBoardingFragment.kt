package az.red.e_commerce_admin_android.content.onBoarding

import android.view.LayoutInflater
import android.view.ViewGroup
import az.red.e_commerce_admin_android.base.BaseFragment
import az.red.e_commerce_admin_android.databinding.FragmentOnBoardingBinding
import kotlin.reflect.KClass

class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding, OnBoardingViewModel>() {

    override val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOnBoardingBinding
        get() = FragmentOnBoardingBinding::inflate
    override val kClass: KClass<OnBoardingViewModel>
        get() = OnBoardingViewModel::class

    override val bindViews: FragmentOnBoardingBinding.() -> Unit = {

    }

}