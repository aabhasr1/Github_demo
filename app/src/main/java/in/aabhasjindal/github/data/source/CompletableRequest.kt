package `in`.aabhasjindal.github.data.source

class CompletableRequest<T>(private val request: suspend () -> T) {
    private val onSuccessListeners: MutableList<suspend (T) -> Unit> = mutableListOf()
    private val onErrorListeners: MutableList<suspend (Exception) -> Unit> = mutableListOf()

    fun onSuccess(block: suspend (T) -> Unit) = this.apply { onSuccessListeners.add(block) }

    fun onError(block: suspend (Exception) -> Unit) = this.apply { onErrorListeners.add(block) }

    suspend fun execute(): CompletableResult<T> {
        return try {
            val result = request.invoke()
            onSuccessListeners.forEach {
                it.invoke(result)
            }
            CompletableResult.Success(result)
        } catch (e: Exception) {
            onErrorListeners.forEach {
                it.invoke(e)
            }
            CompletableResult.Failure(e)
        }
    }

    suspend fun execute(
        success: suspend (T) -> Unit,
        error: suspend (Exception) -> Unit
    ): CompletableResult<T> {
        this.apply {
            onSuccessListeners.add(success)
            onErrorListeners.add(error)
            return execute()
        }
    }
}