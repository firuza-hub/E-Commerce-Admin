package az.red.e_commerce_admin_android.data.remote

import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        val headers = HashMap<String, String>()
        // We add key and value in header for example:
        // headers["Accept"] = "application/json"

        return chain.proceed(request.headers(headers.toHeaders()).build())
    }
}