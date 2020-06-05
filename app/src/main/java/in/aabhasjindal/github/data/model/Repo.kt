package `in`.aabhasjindal.github.data.model

import androidx.annotation.Keep

@Keep
data class Repo(
    val id: Long,
    val name: String,
    val user: User,
    val description: String,
    val language: String
)