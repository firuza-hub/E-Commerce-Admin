package az.red.e_commerce_admin_android.ui.screens.fill_profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.user.UserRepository
import az.red.e_commerce_admin_android.data.remote.user.dto.request.FillProfileRequest
import az.red.e_commerce_admin_android.data.remote.user.dto.response.UserResponse
import az.red.e_commerce_admin_android.ui.common.state.ErrorState
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.ui.screens.register.*
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FillProfileViewModel(private val repository: UserRepository) : BaseViewModel() {

    init {
        getCurrentUser()
    }

    val fillProfileState = MutableStateFlow(FillProfileState.NULL)

    private fun getCurrentUser() {
        viewModelScope.launch(Dispatchers.IO) {
            triggerEvent(UIEvent.Message("Success!"))
            repository.getCurrentUser().collect { networkResult ->
                when (networkResult) {
                    is NetworkResult.Success -> {
                        triggerEvent(UIEvent.Message("Success get current user!"))
                        triggerEvent(UIEvent.Navigate(Graph.MAIN))
                        val data = networkResult.data
                        fillProfileState.value = fillProfileState.value.copy(
                            "${data!!.firstName} ${data.lastName}",
                            data.login, data.email
                        )
                    }
                    is NetworkResult.Empty -> Log.i("GET_CURRENT_USER_REQUEST", "Empty")
                    is NetworkResult.Error -> {
                        Log.i("GET_CURRENT_USER_REQUEST", "Error: ${networkResult.message}")
                        networkResult.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                    }
                    is NetworkResult.Exception -> {
                        Log.e("GET_CURRENT_USER_REQUEST", "Exception: ${networkResult.message}")
                        networkResult.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                    }
                    is NetworkResult.Loading -> Log.i("GET_CURRENT_USER_REQUEST", "Loading")
                }
            }
        }
    }

    private fun updateCurrentUser(dto: FillProfileRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            triggerEvent(UIEvent.Message("Success!"))
            repository.fillProfile(dto).collect { networkResult ->
                when (networkResult) {
                    is NetworkResult.Success -> {
                        triggerEvent(UIEvent.Message("Success update current user!"))
                        triggerEvent(UIEvent.Navigate(Graph.MAIN))
                    }
                    is NetworkResult.Empty -> Log.i("UPDATE_PROFILE_REQUEST", "Empty")
                    is NetworkResult.Error -> {
                        Log.i("UPDATE_PROFILE_REQUEST", "Error: ${networkResult.message}")
                        handleErrorResponse(networkResult.data!!)
                        networkResult.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                    }
                    is NetworkResult.Exception -> {
                        Log.e("UPDATE_PROFILE_REQUEST", "Exception: ${networkResult.message}")
                        networkResult.message?.let { m -> triggerEvent(UIEvent.Error(m)) }
                    }
                    is NetworkResult.Loading -> Log.i("UPDATE_PROFILE_REQUEST", "Loading")
                }
            }
        }
    }

    private fun handleErrorResponse(data: UserResponse) {
        if (data.firstName != null && data.firstName.isNotEmpty() && data.lastName != null && data.lastName.isNotEmpty()) {
            fillProfileState.value = fillProfileState.value.copy(
                errorState = fillProfileState.value.errorState.copy(
                    fullNameErrorState = ErrorState(
                        hasError = true,
                        errorMessage = "${data.firstName} ${data.lastName}"
                    )
                )
            )
        }

        if (data.login != null && data.login.isNotEmpty()) {
            fillProfileState.value = fillProfileState.value.copy(
                errorState = fillProfileState.value.errorState.copy(
                    emailErrorState = ErrorState(hasError = true, errorMessage = data.login)
                )
            )
        }


        if (data.email != null && data.email.isNotEmpty()) {
            fillProfileState.value = fillProfileState.value.copy(
                errorState = fillProfileState.value.errorState.copy(
                    emailErrorState = ErrorState(hasError = true, errorMessage = data.email)
                )
            )
        }

        if (data.birthdate != null && data.birthdate.isNotEmpty()) {
            fillProfileState.value = fillProfileState.value.copy(
                errorState = fillProfileState.value.errorState.copy(
                    dateOfBirthErrorState = ErrorState(
                        hasError = true,
                        errorMessage = data.birthdate
                    )
                )
            )
        }
        if (data.telephone != null && data.telephone.isNotEmpty()) {
            fillProfileState.value = fillProfileState.value.copy(
                errorState = fillProfileState.value.errorState.copy(
                    phoneNumberErrorState = ErrorState(
                        hasError = true,
                        errorMessage = data.telephone
                    )
                )
            )
        }
        if (data.gender != null && data.gender.isNotEmpty()) {
            fillProfileState.value = fillProfileState.value.copy(
                errorState = fillProfileState.value.errorState.copy(
                    genderErrorState = ErrorState(hasError = true, errorMessage = data.gender)
                )
            )
        }
    }

    fun onUiEvent(fillProfileUIEvent: FillProfileUIEvent) {
        when (fillProfileUIEvent) {
            is FillProfileUIEvent.FullNameChanged -> {
                fillProfileState.value = fillProfileState.value.copy(
                    fullName = fillProfileUIEvent.inputValue.trim(),
                    errorState = fillProfileState.value.errorState.copy(
                        fullNameErrorState =
                        if (fillProfileUIEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            fullNameEmptyErrorState
                    ),
                    btnEnabled = fillProfileState.value.fullName.trim()
                        .isNotEmpty() && fillProfileUIEvent.inputValue.trim().isNotEmpty()
                )
            }
            is FillProfileUIEvent.NickNameChanged -> {
                fillProfileState.value = fillProfileState.value.copy(
                    nickName = fillProfileUIEvent.inputValue.trim(),
                    errorState = fillProfileState.value.errorState.copy(
                        nickNameErrorState = if (fillProfileUIEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            nickNameEmptyErrorState
                    )
                )
            }
            is FillProfileUIEvent.EmailChanged -> {
                fillProfileState.value = fillProfileState.value.copy(
                    email = fillProfileUIEvent.inputValue.trim(),
                    errorState = fillProfileState.value.errorState.copy(
                        emailErrorState = if (fillProfileUIEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            emailEmptyErrorState
                    )
                )
            }
            is FillProfileUIEvent.DateOfBirthChanged -> {
                fillProfileState.value = fillProfileState.value.copy(
                    dateOfBirth = fillProfileUIEvent.inputValue.trim(),
                    errorState = fillProfileState.value.errorState.copy(
                        dateOfBirthErrorState = if (fillProfileUIEvent.inputValue.trim()
                                .isNotEmpty()
                        )
                            ErrorState()
                        else
                            emailEmptyErrorState
                    )
                )
            }
            is FillProfileUIEvent.PhoneNumberChanged -> {
                fillProfileState.value = fillProfileState.value.copy(
                    phoneNumber = fillProfileUIEvent.inputValue.trim(),
                    errorState = fillProfileState.value.errorState.copy(
                        phoneNumberErrorState = if (fillProfileUIEvent.inputValue.trim()
                                .isNotEmpty()
                        )
                            ErrorState()
                        else
                            phoneNumberEmptyErrorState
                    )
                )
            }

            is FillProfileUIEvent.GenderChanged -> {
                fillProfileState.value = fillProfileState.value.copy(
                    gender = fillProfileUIEvent.inputValue.trim(),
                    errorState = fillProfileState.value.errorState.copy(
                        genderErrorState = if (fillProfileUIEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            genderEmptyErrorState
                    )
                )
            }

            FillProfileUIEvent.Continue -> {
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    val fillProfileRequest = FillProfileRequest(
                        fillProfileState.value.fullName,
                        fillProfileState.value.nickName,
                        fillProfileState.value.email,
                        fillProfileState.value.dateOfBirth,
                        "+380" + fillProfileState.value.phoneNumber,
                        fillProfileState.value.gender,
                        fillProfileState.value.avatarUrl
                    )
                    updateCurrentUser(fillProfileRequest)
                    fillProfileState.value =
                        fillProfileState.value.copy(isFillProfileSuccessful = true)
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        return when {
            fillProfileState.value.fullName.isEmpty() -> {
                fillProfileState.value = fillProfileState.value.copy(
                    errorState = FillProfileErrorState(
                        fullNameErrorState = fullNameEmptyErrorState
                    )
                )
                false
            }

            fillProfileState.value.nickName.isEmpty() -> {
                fillProfileState.value = fillProfileState.value.copy(
                    errorState = FillProfileErrorState(
                        nickNameErrorState = nickNameEmptyErrorState
                    )
                )
                false
            }

            fillProfileState.value.email.isEmpty() -> {
                fillProfileState.value = fillProfileState.value.copy(
                    errorState = FillProfileErrorState(
                        emailErrorState = emailEmptyErrorState
                    )
                )
                false
            }

            fillProfileState.value.phoneNumber.isEmpty() -> {
                fillProfileState.value = fillProfileState.value.copy(
                    errorState = FillProfileErrorState(
                        phoneNumberErrorState = phoneNumberEmptyErrorState
                    )
                )
                false
            }

            fillProfileState.value.gender.isEmpty() -> {
                fillProfileState.value = fillProfileState.value.copy(
                    errorState = FillProfileErrorState(
                        genderErrorState = genderEmptyErrorState
                    )
                )
                false
            }

            else -> {
                fillProfileState.value =
                    fillProfileState.value.copy(errorState = FillProfileErrorState())
                true
            }
        }
    }
}