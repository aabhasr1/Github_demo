package `in`.aabhasjindal.github.data.repository.implementation

import `in`.aabhasjindal.github.data.repository.contract.UserRepository
import `in`.aabhasjindal.github.data.source.CompletableRequest
import `in`.aabhasjindal.github.data.source.network.NetworkRequest
import `in`.aabhasjindal.github.data.source.network.api.GithubApi
import `in`.aabhasjindal.github.data.source.network.mapper.mapToRepos

class UserRemoteRepository(private val githubApi: GithubApi) : UserRepository {

    override suspend fun fetchRepositories(userName: String) = CompletableRequest {
        val result = NetworkRequest { githubApi.fetchRepos(userName) }.execute()
        result.mapToRepos()
    }
}