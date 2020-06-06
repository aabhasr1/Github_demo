package `in`.aabhasjindal.github.data.repository.implementation

import `in`.aabhasjindal.github.data.model.PR_STATUS
import `in`.aabhasjindal.github.data.repository.contract.UserRepository
import `in`.aabhasjindal.github.data.source.CompletableRequest
import `in`.aabhasjindal.github.data.source.network.NetworkRequest
import `in`.aabhasjindal.github.data.source.network.api.GithubApi
import `in`.aabhasjindal.github.data.source.network.mapper.mapToPullRequests
import `in`.aabhasjindal.github.data.source.network.mapper.mapToRepos
import timber.log.Timber

class UserRemoteRepository(private val githubApi: GithubApi) : UserRepository {

    override suspend fun fetchRepositories(userName: String) = CompletableRequest {
        val result = NetworkRequest { githubApi.fetchRepos(userName) }.execute()
        result.mapToRepos()
    }

    override suspend fun fetchPR(
        userName: String,
        repoName: String,
        state: PR_STATUS
    ) = CompletableRequest {
        val result =
            NetworkRequest { githubApi.fetchAllPR(userName, repoName, state.status) }.execute()
        Timber.d(result.size.toString())
        result.mapToPullRequests()
    }
}