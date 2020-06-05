package `in`.aabhasjindal.github.data.source.network.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BaseResponse<T>(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: T?,
    @SerializedName("total_count")
    val totalCount: Int?
)