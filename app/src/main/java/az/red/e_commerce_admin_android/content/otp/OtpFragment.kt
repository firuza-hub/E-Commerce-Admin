package az.red.e_commerce_admin_android.content.otp

import android.view.LayoutInflater
import android.view.ViewGroup
import az.red.e_commerce_admin_android.base.BaseFragment
import az.red.e_commerce_admin_android.databinding.FragmentOtpBinding
import kotlin.reflect.KClass

class OtpFragment : BaseFragment<FragmentOtpBinding, OtpVIewModel>() {

    override val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOtpBinding
        get() = FragmentOtpBinding::inflate
    override val kClass: KClass<OtpVIewModel>
        get() = OtpVIewModel::class

    override val bindViews: FragmentOtpBinding.() -> Unit = {

    }

}