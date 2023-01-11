package az.red.e_commerce_admin_android.content.register

import android.view.LayoutInflater
import android.view.ViewGroup
import az.red.e_commerce_admin_android.base.BaseFragment
import az.red.e_commerce_admin_android.databinding.FragmentRegisterBinding
import kotlin.reflect.KClass

class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    override val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate
    override val kClass: KClass<RegisterViewModel>
        get() = RegisterViewModel::class

    override val bindViews: FragmentRegisterBinding.() -> Unit = {

    }

}