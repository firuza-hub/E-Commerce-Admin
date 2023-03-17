package az.red.e_commerce_admin_android.ui.screens.bottomnav.profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.user.UserRepository
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.ui.screens.fill_profile.UserProfileState
import az.red.e_commerce_admin_android.utils.NetworkResult
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: UserRepository) : BaseViewModel() {

    val currentLanguage = MutableStateFlow("")
    val isDarkMode = MutableStateFlow(false)

    private val _userProfileState = MutableStateFlow(UserProfileState.NULL)
    val userProfileState = _userProfileState.asStateFlow()

    init {
        getCurrentUser()
        currentLanguageCheck()
        appThemeModeCheck()
    }

    private fun getCurrentUser() {
        viewModelScope.launch(Dispatchers.IO) {
            triggerEvent(UIEvent.Message("Success!"))
            repository.getCurrentUser().collect { networkResult ->
                when (networkResult) {
                    is NetworkResult.Success -> {
                        triggerEvent(UIEvent.Message("Success get current user!"))
                        triggerEvent(UIEvent.Navigate(Graph.MAIN))
                        val data = networkResult.data
                        _userProfileState.value = _userProfileState.value.copy(
                            fullName = "${data!!.firstName} ${data.lastName}",
                            nickName = data.login, email = data.email, avatarUrl = data.avatarUrl
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

    fun logOut() {
        sessionManager.removeAuthToken()
        triggerEvent(UIEvent.Navigate(Graph.AUTH))
    }

    fun saveCurrentLanguage(language: String) {
        sessionManager.saveCurrentLanguage(language)
    }

    fun saveAppThemeMode(isDarkMode: Boolean) {
        sessionManager.saveDarkMode(isDarkMode)
    }

    private fun currentLanguageCheck() {
        sessionManager.fetchCurrentLanguage().let { language ->
            if (!language.isNullOrEmpty()) {
                currentLanguage.value = language.toString()
            } else {
                currentLanguage.value = "US"
            }
        }
    }

    private fun appThemeModeCheck() {
        sessionManager.fetchDarkMode().let { _isDarkMode ->
            isDarkMode.value = _isDarkMode
        }
    }
}