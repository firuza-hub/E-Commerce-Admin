package az.red.e_commerce_admin_android.data.remote

import android.util.Log
import az.red.e_commerce_admin_android.utils.EventBus
import az.red.e_commerce_admin_android.utils.SessionManager
import az.red.e_commerce_admin_android.utils.enums.AuthenticationStatus
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val sessionManager: SessionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val request = chain.request().newBuilder()

            val headers = HashMap<String, String>()
            // We add key and value in header for example:
            if ((chain.request().url.toString().contains(EndPoints.LOGIN)
                        || !chain.request().url.toString().contains(EndPoints.REGISTER)).not()
            ) {
                sessionManager.fetchAuthToken()?.let { headers["Authorization"] = it }
            }

            Log.i("LOGIN_REQUEST", "interceptor - > ${chain.request().url}")
            val response = chain.proceed(request.headers(headers.toHeaders()).build())
            if (response.code == 401) {
                sessionManager.removeAuthToken()
                GlobalScope.launch { EventBus.publish(AuthenticationStatus.UNAUTHENTICATED)}
            }
            return response
        } catch (ex: java.lang.Exception) {
            Log.i("LOGIN_REQUEST", "interceptor - > exception $ex")
            throw ex
        }
    }
}