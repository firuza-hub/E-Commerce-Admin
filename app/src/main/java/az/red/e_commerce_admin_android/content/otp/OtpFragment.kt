package az.red.e_commerce_admin_android.content.otp

import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.base.BaseFragment
import az.red.e_commerce_admin_android.databinding.FragmentOtpBinding
import kotlin.reflect.KClass

class OtpFragment : BaseFragment<FragmentOtpBinding, OtpVIewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_otp
    override val kClass: KClass<OtpVIewModel>
        get() = OtpVIewModel::class

    override val bindViews: FragmentOtpBinding.() -> Unit = {

    }


}