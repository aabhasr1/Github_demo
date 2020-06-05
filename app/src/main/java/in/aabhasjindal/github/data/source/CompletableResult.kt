package `in`.aabhasjindal.github.data.source

sealed class CompletableResult<T> {
    class Success<T>(val data: T) : CompletableResult<T>()
    class Failure<T>(val error: Exception) : CompletableResult<T>()
}