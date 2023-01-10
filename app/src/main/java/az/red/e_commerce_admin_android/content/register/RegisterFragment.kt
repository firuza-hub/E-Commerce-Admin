package az.red.e_commerce_admin_android.content.register

import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.base.BaseFragment
import az.red.e_commerce_admin_android.databinding.FragmentRegisterBinding
import kotlin.reflect.KClass

class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_register
    override val kClass: KClass<RegisterViewModel>
        get() = RegisterViewModel::class

    override val bindViews: FragmentRegisterBinding.() -> Unit = {

    }
}