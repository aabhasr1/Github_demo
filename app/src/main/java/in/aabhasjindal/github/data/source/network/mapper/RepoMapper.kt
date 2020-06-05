package `in`.aabhasjindal.github.data.source.network.mapper

import `in`.aabhasjindal.github.data.model.Repo
import `in`.aabhasjindal.github.data.source.network.dto.RepoDTO

fun RepoDTO.mapToRepo(): Repo = Repo(
    id!!.toLong(),
    name!!,
    userDTO!!.mapToUser(),
    description ?: "",
    language ?: ""
)

fun List<RepoDTO>.mapToRepos(): List<Repo> = map { it.mapToRepo() }