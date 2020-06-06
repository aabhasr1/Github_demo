package `in`.aabhasjindal.github.data.repository.contract

import `in`.aabhasjindal.github.data.model.PR_STATUS
import `in`.aabhasjindal.github.data.model.PullRequest
import `in`.aabhasjindal.github.data.model.Repo
import `in`.aabhasjindal.github.data.source.CompletableRequest

interface UserRepository {
    suspend fun fetchRepositories(userName: String): CompletableRequest<List<Repo>>

    suspend fun fetchPR(
        userName: String,
        repoName: String,
        state: PR_STATUS
    ): CompletableRequest<List<PullRequest>>
}