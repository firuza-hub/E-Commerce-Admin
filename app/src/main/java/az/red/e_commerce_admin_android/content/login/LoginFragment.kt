package az.red.e_commerce_admin_android.content.login

import android.text.Editable
import android.text.TextWatcher
import androidx.navigation.fragment.findNavController
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.base.BaseFragment
import az.red.e_commerce_admin_android.data.remote.auth.dto.request.LoginRequest
import az.red.e_commerce_admin_android.databinding.FragmentLoginBinding
import kotlin.reflect.KClass

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_login
    override val kClass: KClass<LoginViewModel>
        get() = LoginViewModel::class

    override val bindViews: FragmentLoginBinding.() -> Unit = {


        etEmail.addTextChangedListener(textWatcher)
        etPassword.addTextChangedListener(textWatcher)


        tvSignUp.setOnClickListener {
            findNavController().navigateUp()
        }
        btnSignIn.setOnClickListener {
            viewModel.login(LoginRequest(etEmail.text.toString(), etPassword.text.toString()))
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            binding.btnSignIn.isEnabled =
                (binding.etEmail.text.isNotEmpty()) || (binding.etPassword.text.isNotEmpty())
            // binding.etPassword.isEndIconCheckable = binding.etPassword.text.isNotEmpty()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    }
}