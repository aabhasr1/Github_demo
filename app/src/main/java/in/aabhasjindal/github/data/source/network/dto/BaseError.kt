package `in`.aabhasjindal.github.data.source.network.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BaseError(
    @SerializedName("documentation_url")
    val documentationUrl: String?,
    @SerializedName("errors")
    val errors: List<Error>?,
    @SerializedName("message")
    val message: String?
)

@Keep
data class Error(
    @SerializedName("code")
    val code: String?,
    @SerializedName("field")
    val `field`: String?,
    @SerializedName("resource")
    val resource: String?
)