package `in`.aabhasjindal.github.data.source.network.api

import `in`.aabhasjindal.github.data.source.network.dto.BaseResponse
import `in`.aabhasjindal.github.data.source.network.dto.RepoDTO
import `in`.aabhasjindal.github.data.source.network.dto.UserDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("search/users")
    suspend fun findUser(@Query("q") keyword: String): Response<BaseResponse<List<UserDTO>>>

    @GET("users/{name}/repos")
    suspend fun fetchRepos(@Path("name") name: String): Response<List<RepoDTO>>
}