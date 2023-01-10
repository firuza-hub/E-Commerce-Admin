package az.red.e_commerce_admin_android.content.onBoarding

import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.base.BaseFragment
import az.red.e_commerce_admin_android.databinding.FragmentOnBoardingBinding
import kotlin.reflect.KClass

class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding, OnBoardingViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_on_boarding
    override val kClass: KClass<OnBoardingViewModel>
        get() = OnBoardingViewModel::class

    override val bindViews: FragmentOnBoardingBinding.() -> Unit = {
    }

}