package `in`.aabhasjindal.github.data.source.network

import `in`.aabhasjindal.github.data.source.network.dto.BaseError
import com.google.gson.Gson
import retrofit2.Response

object ExceptionResolver {
    private val gson = Gson()

    fun resolve(e: Exception): NetworkException {
        return when (e) {
            is EmptyResponseException -> NetworkException(412, "response body is empty")
            is NetworkException -> NetworkException(e.errorCode, e.message)
            else -> NetworkException(500, e.message ?: "")
        }
    }

    fun <T> handleHttpError(apiResult: Response<T>): NetworkException {
        try {
            apiResult.errorBody()?.string()?.let {
                gson.fromJson(it, BaseError::class.java)?.message?.let { error ->
                    return NetworkException(apiResult.code(), error)
                }
            }
        } catch (e: Exception) {
        }
        return NetworkException(apiResult.code(), "Something went wrong")
    }
}