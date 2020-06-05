package `in`.aabhasjindal.github.data.repository.contract

import `in`.aabhasjindal.github.data.model.Repo
import `in`.aabhasjindal.github.data.source.CompletableRequest

interface UserRepository {
    suspend fun fetchRepositories(userName: String): CompletableRequest<List<Repo>>
}