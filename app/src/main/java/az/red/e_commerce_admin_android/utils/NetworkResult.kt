package az.red.e_commerce_admin_android.utils

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

sealed class NetworkResult<T>(
    val data: T? = null, val message: String? = null
) {
    class Empty<T> : NetworkResult<T>()
    class Loading<T> : NetworkResult<T>()
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String?, code: Int, data: T? = null) : NetworkResult<T>(data, message)
    class Exception<T>(exception: String) : NetworkResult<T>(message = exception)
}


fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): Flow<NetworkResult<T>> = flow {
    try {
        Log.i("LOGIN_REQUEST", "handleApi - > start")
        val response = execute()
        val body = response.body()
        emit(NetworkResult.Loading())

        if (response.isSuccessful && body != null) {

            Log.i("LOGIN_REQUEST", "handleApi - > success")
            emit(NetworkResult.Success(body))

        } else {
            emit(NetworkResult.Error(code = response.code(), message = response.message()))
        }
    } catch (e: HttpException) {
        emit(NetworkResult.Error<T>(message = e.message(), code = e.code()))
    } catch (e: Throwable) {
        emit(NetworkResult.Exception<T>(exception = e.message!!))
    }
}
