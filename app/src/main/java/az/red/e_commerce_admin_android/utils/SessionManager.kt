package az.red.e_commerce_admin_android.utils

import android.content.Context
import android.content.SharedPreferences
import az.red.e_commerce_admin_android.R

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val CURRENT_LANGUAGE = "current_language"
        const val DARK_MODE = "dark_mode"
        const val REMEMBER_ME = "remember_me"
    }

    fun saveAuthToken(token: String, rememberMe:Boolean) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.putBoolean(REMEMBER_ME, rememberMe)
        editor.apply()
        println("Token Saved: $token")
    }

    fun saveCurrentLanguage(language: String) {
        val editor = prefs.edit()
        editor.putString(CURRENT_LANGUAGE, language)
        editor.apply()
        println("Language Saved: $language")
    }

    fun saveDarkMode(isDarkMode: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(DARK_MODE, isDarkMode)
        editor.apply()
        println("Dark Mode Saved: $isDarkMode")
    }

    fun removeAuthToken() {
        val editor = prefs.edit()
        editor.remove(USER_TOKEN)
        editor.remove(REMEMBER_ME)
        editor.apply()
        println("Token Removed")
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun fetchCurrentLanguage(): String? {
        return prefs.getString(CURRENT_LANGUAGE, null)
    }

    fun fetchDarkMode(): Boolean {
        return prefs.getBoolean(DARK_MODE, false)
    }

    fun fetchRememberMe(): Boolean {
        return prefs.getBoolean(REMEMBER_ME, false)
    }
}