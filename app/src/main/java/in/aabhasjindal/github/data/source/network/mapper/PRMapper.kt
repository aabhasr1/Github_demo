package `in`.aabhasjindal.github.data.source.network.mapper

import `in`.aabhasjindal.github.data.model.PR_STATUS
import `in`.aabhasjindal.github.data.model.PullRequest
import `in`.aabhasjindal.github.data.source.network.dto.PullRequestDTO
import `in`.aabhasjindal.github.utils.UiUtils

fun PullRequestDTO.mapToPullRequest(): PullRequest = PullRequest(
    title!!,
    UiUtils.convertToNewFormat(createdAt),
    UiUtils.convertToNewFormat(closedAt),
    when (state) {
        PR_STATUS.OPEN.status -> PR_STATUS.OPEN
        PR_STATUS.CLOSED.status -> PR_STATUS.CLOSED
        else -> PR_STATUS.ALL
    },
    number!!,
    userDTO!!.mapToUser(),
    htmlUrl!!
)

fun List<PullRequestDTO>.mapToPullRequests() = map { it.mapToPullRequest() }