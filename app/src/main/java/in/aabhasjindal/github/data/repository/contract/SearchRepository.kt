package `in`.aabhasjindal.github.data.repository.contract

import `in`.aabhasjindal.github.data.model.User
import `in`.aabhasjindal.github.data.source.CompletableRequest

interface SearchRepository {
    suspend fun searchUser(keyword: String): CompletableRequest<List<User>>
}