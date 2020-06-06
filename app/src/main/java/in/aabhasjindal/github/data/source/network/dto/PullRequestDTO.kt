package `in`.aabhasjindal.github.data.source.network.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PullRequestDTO(
    @SerializedName("assignee")
    val assignee: Any?,
    @SerializedName("assignees")
    val assignees: List<Any>?,
    @SerializedName("author_association")
    val authorAssociation: String?,
    @SerializedName("body")
    val body: String?,
    @SerializedName("closed_at")
    val closedAt: String?,
    @SerializedName("comments_url")
    val commentsUrl: String?,
    @SerializedName("commits_url")
    val commitsUrl: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("diff_url")
    val diffUrl: String?,
    @SerializedName("draft")
    val draft: Boolean?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("issue_url")
    val issueUrl: String?,
    @SerializedName("locked")
    val locked: Boolean?,
    @SerializedName("merge_commit_sha")
    val mergeCommitSha: String?,
    @SerializedName("merged_at")
    val mergedAt: Any?,
    @SerializedName("milestone")
    val milestone: Any?,
    @SerializedName("node_id")
    val nodeId: String?,
    @SerializedName("number")
    val number: Int?,
    @SerializedName("patch_url")
    val patchUrl: String?,
    @SerializedName("review_comment_url")
    val reviewCommentUrl: String?,
    @SerializedName("review_comments_url")
    val reviewCommentsUrl: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("statuses_url")
    val statusesUrl: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("user")
    val userDTO: UserDTO?
)