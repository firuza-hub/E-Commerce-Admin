package az.red.e_commerce_admin_android.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import retrofit2.Response

sealed class NetworkResult<T>(
    val data: T? = null, val message: String? = null
) {
    class Empty<T> : NetworkResult<T>()
    class Loading<T> : NetworkResult<T>()
    class Success<T>(data: T?) : NetworkResult<T>(data)
    class Error<T>(message: String?, code: Int, data: T? = null) : NetworkResult<T>(data, message)
    class Exception<T>(exception: String) : NetworkResult<T>(message = exception)
}

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): StateFlow<NetworkResult<T>> {
    val apiState = MutableStateFlow<NetworkResult<T>>(NetworkResult.Empty())
    try {
        val response = execute()
        val body = response.body()
        apiState.emit(NetworkResult.Loading())

        if (response.isSuccessful && body != null) {
            apiState.emit(NetworkResult.Success(body))

        } else {
            apiState.emit(NetworkResult.Error(code = response.code(), message = response.message()))
        }
    } catch (e: HttpException) {
        apiState.emit(NetworkResult.Error<T>(message = e.message(), code = e.code()))
    } catch (e: Throwable) {
        apiState.emit(NetworkResult.Exception<T>(exception = e.message!!))
    }
    return apiState
}

