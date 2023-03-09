package az.red.e_commerce_admin_android.utils

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.java.KoinJavaComponent.inject
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
inline fun <reified T : Any> handleApi(
    crossinline execute: suspend () -> Response<T>
): Flow<NetworkResult<T>> = flow {
    try {
        val response = execute()
        val body = response.body()
        emit(NetworkResult.Loading())

        if (response.isSuccessful && body != null) {
            emit(NetworkResult.Success(body))
        } else {
            val gson: Gson by inject(Gson::class.java)
            val e = response.errorBody()?.let { gson.fromJson(it.string(), T::class.java) }
            emit(
                NetworkResult.Error(
                    code = response.code(),
                    message = response.message(),
                    data = e as T
                )
            )
        }
    } catch (e: HttpException) {
        emit(NetworkResult.Error(message = e.message(), code = e.code()))
    } catch (e: Throwable) {
        emit(NetworkResult.Exception(exception = e.message!!))
    }
}
