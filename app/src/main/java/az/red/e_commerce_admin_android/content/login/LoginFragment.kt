package az.red.e_commerce_admin_android.content.login

import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.base.BaseFragment
import az.red.e_commerce_admin_android.databinding.FragmentLoginBinding
import kotlin.reflect.KClass

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_login
    override val kClass: KClass<LoginViewModel>
        get() = LoginViewModel::class

    override val bindViews: FragmentLoginBinding.() -> Unit = {

    }

}