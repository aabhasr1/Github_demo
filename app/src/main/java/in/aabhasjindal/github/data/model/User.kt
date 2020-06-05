package `in`.aabhasjindal.github.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class User(
    val name: String,
    val id: Long,
    val photo: String,
    val clickable: Boolean
) : Parcelable