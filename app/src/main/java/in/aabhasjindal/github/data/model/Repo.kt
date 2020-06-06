package `in`.aabhasjindal.github.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Repo(
    val id: Long,
    val name: String,
    val user: User,
    val description: String,
    val language: String
) : Parcelable