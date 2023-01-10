package az.red.e_commerce_admin_android.content.login

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.base.BaseFragment
import az.red.e_commerce_admin_android.databinding.FragmentLoginBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import kotlin.reflect.KClass


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    lateinit var _email: EditText
    lateinit var _password: EditText
    lateinit var _btnSignIn: MaterialButton
    lateinit var _etPasswordLayout: TextInputLayout

    override val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate
    override val kClass: KClass<LoginViewModel>
        get() = LoginViewModel::class

    override val bindViews: FragmentLoginBinding.() -> Unit = {
        etPasswordLayout.isEndIconCheckable = false
        _email = this.etEmail
        _password = this.etPassword
        _btnSignIn = this.btnSignIn
        _etPasswordLayout = etPasswordLayout
        _email.addTextChangedListener(textWatcher)
        _password.addTextChangedListener(textWatcher)

       tvSignUp.setOnClickListener{findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())}
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            _btnSignIn.isEnabled = (_email.text.isNotEmpty()) || (_password.text.isNotEmpty())
            _etPasswordLayout.isEndIconCheckable = _password.text.isNotEmpty()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }
}