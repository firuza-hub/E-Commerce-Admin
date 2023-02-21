package az.red.e_commerce_admin_android.ui.screens.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.auth.AuthRepository
import az.red.e_commerce_admin_android.ui.common.state.ErrorState
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepo: AuthRepository
) : BaseViewModel() {


    val isLoggedIn = MutableStateFlow(false)
    val loginState = MutableStateFlow(LoginState.NULL)

    init {
        authorizationCheck()
    }

    private fun authorizationCheck() {
        sessionManager.fetchAuthToken().let {
            if (it.isNullOrEmpty()) {
                isLoggedIn.value = false
            } else {
                Log.i("BASE_VIEW_MODEL", "Token is empty. Redirect to login")
                isLoggedIn.value = true
                triggerEvent(UIEvent.Navigate(Graph.MAIN))
            }
        }
    }

    private fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            authRepo.login(loginState.value.toLoginRequest()).collect {
                when (it) {
                    is NetworkResult.Success -> {
                        sessionManager.saveAuthToken(it.data!!.token, loginState.value.rememberMe)
                        triggerEvent(UIEvent.Message("Success!"))
                        triggerEvent(UIEvent.Navigate(Graph.MAIN))
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

            is LoginUIEvent.EmailChanged -> {
                loginState.value = loginState.value.copy(
                    email = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        emailOrMobileErrorState =
                        if (loginUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            emailOrMobileEmptyErrorState
                    ),
                    btnEnabled = loginState.value.password.trim().isNotEmpty() && loginUiEvent.inputValue.trim().isNotEmpty()
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginState.value = loginState.value.copy(
                    password = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        passwordErrorState =
                        if (loginUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            passwordEmptyErrorState
                    ),
                    btnEnabled = loginState.value.email.trim().isNotEmpty() && loginUiEvent.inputValue.trim().isNotEmpty()
                )
            }

            is LoginUIEvent.Submit -> {
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    login()
                    loginState.value = loginState.value.copy(isLoginSuccessful = true)
                }
            }
            is LoginUIEvent.RememberMeChanged -> {
                loginState.value = loginState.value.copy(
                    rememberMe = loginUiEvent.inputValue
                )
            }
        }
    }

    private fun validateInputs(): Boolean {
        val emailOrMobileString = loginState.value.email.trim()
        val passwordString = loginState.value.password
        return when {
            emailOrMobileString.isEmpty() -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        emailOrMobileErrorState = emailOrMobileEmptyErrorState
                    )
                )
                false
            }

            passwordString.isEmpty() -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        passwordErrorState = passwordEmptyErrorState
                    )
                )
                false
            }

            else -> {
                loginState.value = loginState.value.copy(errorState = LoginErrorState())
                true
            }
        }
    }

}

