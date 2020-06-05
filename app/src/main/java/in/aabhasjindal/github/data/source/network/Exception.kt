package `in`.aabhasjindal.github.data.source.network

class NetworkException(val errorCode: Int, override val message: String = "") : Exception()
class EmptyResponseException() : Exception()