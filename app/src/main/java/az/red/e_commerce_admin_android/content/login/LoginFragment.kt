package az.red.e_commerce_admin_android.content.login

import android.view.LayoutInflater
import android.view.ViewGroup
import az.red.e_commerce_admin_android.base.BaseFragment
import az.red.e_commerce_admin_android.databinding.FragmentLoginBinding
import kotlin.reflect.KClass


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate
    override val kClass: KClass<LoginViewModel>
        get() = LoginViewModel::class

    override val bindViews: FragmentLoginBinding.() -> Unit = {

    }
}