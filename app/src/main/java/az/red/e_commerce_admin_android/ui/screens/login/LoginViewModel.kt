package az.red.e_commerce_admin_android.ui.screens.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.auth.AuthRepository
import az.red.e_commerce_admin_android.ui.common.state.ErrorState
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepo: AuthRepository
) : BaseViewModel() {

    val loginState = MutableStateFlow(LoginState.NULL)
    private fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            authRepo.login(loginState.value.toLoginRequest()).collect {
                when (it) {
                    is NetworkResult.Success -> {
                        sessionManager.saveAuthToken(it.data!!.token)
                        triggerEvent(UIEvent.Message("Success!"))
                        Log.i("LOGIN_REQUEST", "Success: ${it.data.token}")
                    }
                    is NetworkResult.Empty -> Log.i("LOGIN_REQUEST", "Empty")
                    is NetworkResult.Error -> {
                        Log.i("LOGIN_REQUEST", "Error: ${it.message}")
                        it.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                    }
                    is NetworkResult.Exception -> {
                        Log.e("LOGIN_REQUEST", "Exception: ${it.message}")
                        it.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                    }
                    is NetworkResult.Loading -> Log.i("LOGIN_REQUEST", "Loading")
                }
            }
        }
    }

    fun onUiEvent(loginUiEvent: LoginUIEvent) {
        when (loginUiEvent) {

            // Email/Mobile changed
            is LoginUIEvent.EmailChanged -> {
                loginState.value = loginState.value.copy(
                    email = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        emailOrMobileErrorState =
                        if (loginUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            emailOrMobileEmptyErrorState
                    )
                )
            }

            // Password changed
            is LoginUIEvent.PasswordChanged -> {
                loginState.value = loginState.value.copy(
                    password = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        passwordErrorState =
                        if (loginUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            passwordEmptyErrorState
                    )
                )
            }

            // Submit Login
            is LoginUIEvent.Submit -> {
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    login()
                    loginState.value = loginState.value.copy(isLoginSuccessful = true)
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val emailOrMobileString = loginState.value.email.trim()
        val passwordString = loginState.value.password
        return when {

            // Email/Mobile empty
            emailOrMobileString.isEmpty() -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        emailOrMobileErrorState = emailOrMobileEmptyErrorState
                    )
                )
                false
            }

            //Password Empty
            passwordString.isEmpty() -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        passwordErrorState = passwordEmptyErrorState
                    )
                )
                false
            }

            // No errors
            else -> {
                // Set default error state
                loginState.value = loginState.value.copy(errorState = LoginErrorState())
                true
            }
        }
    }

}

