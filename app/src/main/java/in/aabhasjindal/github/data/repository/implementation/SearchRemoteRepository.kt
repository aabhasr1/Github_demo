package `in`.aabhasjindal.github.data.repository.implementation

import `in`.aabhasjindal.github.data.repository.contract.SearchRepository
import `in`.aabhasjindal.github.data.source.CompletableRequest
import `in`.aabhasjindal.github.data.source.network.NetworkRequest
import `in`.aabhasjindal.github.data.source.network.api.GithubApi
import `in`.aabhasjindal.github.data.source.network.mapper.mapToUsers

class SearchRemoteRepository(private val githubApi: GithubApi) : SearchRepository {

    override suspend fun searchUser(keyword: String) = CompletableRequest {
        val result = NetworkRequest { githubApi.findUser(keyword) }.execute()
        result.items?.mapToUsers() ?: emptyList()
    }
}