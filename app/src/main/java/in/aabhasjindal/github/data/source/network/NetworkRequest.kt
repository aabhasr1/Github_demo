package `in`.aabhasjindal.github.data.source.network

import retrofit2.Response

class NetworkRequest<R>(private val block: suspend () -> Response<R>) {

    @Throws(NetworkException::class)
    suspend fun execute(): R {
        try {
            val apiResult = block.invoke()
            if (apiResult.isSuccessful) {
                apiResult.body()?.let { data -> return data }
                    ?: throw EmptyResponseException()
            } else {
                throw ExceptionResolver.handleHttpError(apiResult)
            }
        } catch (e: Exception) {
            throw ExceptionResolver.resolve(e)
        }
    }
}