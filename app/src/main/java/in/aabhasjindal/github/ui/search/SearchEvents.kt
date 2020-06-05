package `in`.aabhasjindal.github.ui.search

import `in`.aabhasjindal.github.data.model.User
import `in`.aabhasjindal.github.ui.base.BaseEvents

interface SearchEvents : BaseEvents {
    fun onUserSelected(user: User)
}