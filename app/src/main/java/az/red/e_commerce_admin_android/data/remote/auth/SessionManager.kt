package az.red.e_commerce_admin_android.data.remote.auth

import android.content.Context
import android.content.SharedPreferences
import az.red.e_commerce_admin_android.R

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
        println("Token Saved: $token")
    }

    fun removeAuthToken() {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, "")
        editor.apply()
        println("Token Removed")
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}