package az.red.e_commerce_admin_android.content.register

import android.content.res.ColorStateList
import android.content.res.Resources
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import az.red.e_commerce_admin_android.R
import az.red.e_commerce_admin_android.base.BaseFragment
import az.red.e_commerce_admin_android.databinding.FragmentRegisterBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import kotlin.reflect.KClass

class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    lateinit var _email: EditText
    lateinit var _password: EditText
    lateinit var _btnSignIn: MaterialButton
    lateinit var _etPasswordLayout: TextInputLayout

    override val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate
    override val kClass: KClass<RegisterViewModel>
        get() = RegisterViewModel::class

    override val bindViews: FragmentRegisterBinding.() -> Unit = {

        etPasswordLayout.isEndIconCheckable = false
        _email = this.etEmail
        _password = this.etPassword
        _btnSignIn = this.btnSignIn
        _etPasswordLayout = etPasswordLayout
        _email.addTextChangedListener(textWatcher)
        _password.addTextChangedListener(textWatcher)

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