package `in`.aabhasjindal.github.ui.pullrequest

import `in`.aabhasjindal.github.data.model.PullRequest
import `in`.aabhasjindal.github.ui.base.BaseEvents

interface PREvents : BaseEvents {
    fun onPRSelected(pullRequest: PullRequest)
}