package az.red.e_commerce_admin_android.ui.screens.register

import android.util.Log
import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.auth.AuthRepository
import az.red.e_commerce_admin_android.data.remote.auth.dto.request.LoginRequest
import az.red.e_commerce_admin_android.data.remote.auth.dto.response.RegisterResponse
import az.red.e_commerce_admin_android.ui.common.state.ErrorState
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authRepo: AuthRepository
) : BaseViewModel() {

    val registerState = MutableStateFlow(RegisterState.NULL)

    private fun register() = viewModelScope.launch {
        authRepo.register(registerState.value.toRegisterRequest()).collect {
            when (it) {
                is NetworkResult.Empty -> Log.i("REGISTER_REQUEST", "Empty")
                is NetworkResult.Error -> {
                    Log.i("REGISTER_REQUEST", "Error: ${it.message}")
                    handleErrorResponse(it.data!!)
                    it.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                }

                is NetworkResult.Exception -> Log.i("REGISTER_REQUEST", "Exception")
                is NetworkResult.Loading -> Log.i("REGISTER_REQUEST", "Loading")
                is NetworkResult.Success -> {
                    triggerEvent(UIEvent.Message("Success!"))
                    authRepo.login(
                        LoginRequest(
                            registerState.value.login, registerState.value.password
                        )
                    ).collect { loginResp ->
                        when (loginResp) {
                            is NetworkResult.Success -> {
                                sessionManager.saveAuthToken(loginResp.data!!.token!!, true)
                                triggerEvent(UIEvent.Message("Success!"))
                                triggerEvent(UIEvent.Navigate(Graph.MAIN))
                                Log.i("LOGIN_REQUEST", "Success: ${loginResp.data.token}")
                            }
                            is NetworkResult.Empty -> Log.i("LOGIN_REQUEST", "Empty")
                            is NetworkResult.Error -> {
                                Log.i("LOGIN_REQUEST", "Error: ${loginResp.message}")
                                loginResp.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                            }
                            is NetworkResult.Exception -> {
                                Log.e("LOGIN_REQUEST", "Exception: ${loginResp.message}")
                                loginResp.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                            }
                            is NetworkResult.Loading -> Log.i("LOGIN_REQUEST", "Loading")
                        }
                    }
                }
            }
        }
    }

    fun onUiEvent(registerUiEvent: RegisterUIEvent) {
        when (registerUiEvent) {
            is RegisterUIEvent.EmailChanged -> {
                registerState.value = registerState.value.copy(
                    email = registerUiEvent.inputValue.trim(),
                    errorState = registerState.value.errorState.copy(
                        emailErrorState = if (registerUiEvent.inputValue.trim()
                                .isNotEmpty()
                        ) ErrorState()
                        else emailEmptyErrorState
                    ),
                    btnEnabled = registerState.value.password.trim()
                        .isNotEmpty() && registerUiEvent.inputValue.trim().isNotEmpty()
                )
            }
            is RegisterUIEvent.FirstNameChanged -> {
                registerState.value = registerState.value.copy(
                    firstName = registerUiEvent.inputValue.trim(),
                    errorState = registerState.value.errorState.copy(
                        firstNameErrorState = if (registerUiEvent.inputValue.trim()
                                .isNotEmpty()
                        ) ErrorState()
                        else firstNameEmptyErrorState
                    )
                )
            }
            is RegisterUIEvent.LastNameChanged -> {
                registerState.value = registerState.value.copy(
                    lastName = registerUiEvent.inputValue.trim(),
                    errorState = registerState.value.errorState.copy(
                        lastNameErrorState = if (registerUiEvent.inputValue.trim()
                                .isNotEmpty()
                        ) ErrorState()
                        else lastNameEmptyErrorState
                    )
                )
            }
            is RegisterUIEvent.PasswordChanged -> {
                registerState.value = registerState.value.copy(
                    password = registerUiEvent.inputValue.trim(),
                    errorState = registerState.value.errorState.copy(
                        passwordErrorState = if (registerUiEvent.inputValue.trim()
                                .isNotEmpty()
                        ) ErrorState()
                        else passwordEmptyErrorState
                    )
                )
            }
            is RegisterUIEvent.LoginChanged -> {
                registerState.value = registerState.value.copy(
                    login = registerUiEvent.inputValue.trim(),
                    errorState = registerState.value.errorState.copy(
                        loginErrorState = if (registerUiEvent.inputValue.trim()
                                .isNotEmpty()
                        ) ErrorState()
                        else loginNameEmptyErrorState
                    )
                )
            }
            RegisterUIEvent.Submit -> {
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    register()
                    registerState.value = registerState.value.copy(isRegisterSuccessful = true)
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        return when {
            registerState.value.email.isEmpty() -> {
                registerState.value = registerState.value.copy(
                    errorState = RegisterErrorState(
                        emailErrorState = emailEmptyErrorState
                    )
                )
                false
            }

            registerState.value.password.isEmpty() -> {
                registerState.value = registerState.value.copy(
                    errorState = RegisterErrorState(
                        passwordErrorState = passwordEmptyErrorState
                    )
                )
                false
            }

            registerState.value.firstName.isEmpty() -> {
                registerState.value = registerState.value.copy(
                    errorState = RegisterErrorState(
                        firstNameErrorState = firstNameEmptyErrorState
                    )
                )
                false
            }

            registerState.value.lastName.isEmpty() -> {
                registerState.value = registerState.value.copy(
                    errorState = RegisterErrorState(
                        lastNameErrorState = lastNameEmptyErrorState
                    )
                )
                false
            }

            registerState.value.login.isEmpty() -> {
                registerState.value = registerState.value.copy(
                    errorState = RegisterErrorState(
                        loginErrorState = loginNameEmptyErrorState
                    )
                )
                false
            }
            else -> {
                registerState.value = registerState.value.copy(errorState = RegisterErrorState())
                true
            }
        }
    }


    private fun handleErrorResponse(data: RegisterResponse) {
        if (data.password != null && data.password.isNotEmpty()) {
            registerState.value = registerState.value.copy(
                errorState = registerState.value.errorState.copy(
                    passwordErrorState = ErrorState(hasError = true, errorMessage = data.password)
                )
            )
        }
        if (data.email != null && data.email.isNotEmpty()) {
            registerState.value = registerState.value.copy(
                errorState = registerState.value.errorState.copy(
                    emailErrorState = ErrorState(hasError = true, errorMessage = data.email)
                )
            )
        }

        if (data.firstName != null && data.firstName.isNotEmpty()) {
            registerState.value = registerState.value.copy(
                errorState = registerState.value.errorState.copy(
                    firstNameErrorState = ErrorState(hasError = true, errorMessage = data.firstName)
                )
            )
        }
        if (data.lastName != null && data.lastName.isNotEmpty()) {
            registerState.value = registerState.value.copy(
                errorState = registerState.value.errorState.copy(
                    lastNameErrorState = ErrorState(hasError = true, errorMessage = data.lastName)
                )
            )
        }
        if (data.login != null && data.login.isNotEmpty()) {
            registerState.value = registerState.value.copy(
                errorState = registerState.value.errorState.copy(
                    loginErrorState = ErrorState(hasError = true, errorMessage = data.login)
                )
            )
        }
    }
}

