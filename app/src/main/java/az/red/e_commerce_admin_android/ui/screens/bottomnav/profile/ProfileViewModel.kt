package az.red.e_commerce_admin_android.ui.screens.bottomnav.profile

import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.ui.navigation.root.Graph
import az.red.e_commerce_admin_android.utils.UIEvent
import kotlinx.coroutines.flow.MutableStateFlow

class ProfileViewModel : BaseViewModel() {

    val isDarkMode = MutableStateFlow(false)

    init {
        appThemeModeCheck()
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

    private fun appThemeModeCheck() {
        sessionManager.fetchDarkMode().let { _isDarkMode ->
            isDarkMode.value = _isDarkMode
        }
    }
}