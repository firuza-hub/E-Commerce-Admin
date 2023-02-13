package az.red.e_commerce_admin_android.ui.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import az.red.e_commerce_admin_android.base.BaseViewModel
import az.red.e_commerce_admin_android.data.remote.auth.AuthRepository
import az.red.e_commerce_admin_android.data.remote.auth.SessionManager
import az.red.e_commerce_admin_android.data.remote.auth.dto.request.LoginRequest
import az.red.e_commerce_admin_android.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepo: AuthRepository,
    private val sessionManager: SessionManager
) : BaseViewModel() {
    fun login(loginData: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            authRepo.login(loginData).collect {
                when (it) {
                    is NetworkResult.Success -> sessionManager.saveAuthToken(it.data!!.token)
                    is NetworkResult.Empty -> Log.i("LOGIN_REQUEST","Empty")
                    is NetworkResult.Error -> Log.i("LOGIN_REQUEST","Error:  ${it.message}")
                    is NetworkResult.Exception -> Log.i("LOGIN_REQUEST","Exception: ${it.message}")
                    is NetworkResult.Loading -> Log.i("LOGIN_REQUEST","Loading")
                }
            }
        }


    }
}