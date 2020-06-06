package `in`.aabhasjindal.github.data.model

import `in`.aabhasjindal.github.ui.base.RecyclerConfiguration
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.databinding.ObservableField
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class PullRequest(
    val title: String,
    val createdDate: String,
    val closedDate: String,
    val status: PR_STATUS,
    val number: Int,
    val user: User,
    val webUrl: String
) : Parcelable

enum class PR_STATUS(val status: String) {
    OPEN("open"),
    CLOSED("closed"),
    ALL("all")
}

data class RecyclerPullRequestData(
    val recyclerConfiguration: RecyclerConfiguration,
    val list: ObservableField<List<PullRequest>>,
    val loading: ObservableField<Boolean>,
    val noData: ObservableField<Boolean>
)