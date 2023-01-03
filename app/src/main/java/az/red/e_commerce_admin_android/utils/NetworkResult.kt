package az.red.e_commerce_admin_android.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import retrofit2.Response

sealed class NetworkResult<T>(
    val data: T? = null, val message: String? = null
) {
    class Success<T>(data: T?) : NetworkResult<T>(data)
    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(data, message)
    class Loading<T> : NetworkResult<T>()
    class Empty<T> : NetworkResult<T>()
    class Unknown<T>(exception: String) : NetworkResult<T>(message = exception)
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
        } else if (response.message().toString().contains("timeout")) {
            NetworkResult.Error<T>(message = "Timeout")
        } else {
            apiState.emit(
                NetworkResult.Error(
                    message = response.message()
                )
            )
        }
    } catch (e: HttpException) {
        NetworkResult.Error<T>(message = e.message())
    } catch (e: Throwable) {
        NetworkResult.Unknown<T>(exception = e.message!!)
    }
    return apiState
}

