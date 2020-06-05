package `in`.aabhasjindal.github.ui.dashboard

import `in`.aabhasjindal.github.data.model.User
import `in`.aabhasjindal.github.ui.base.BaseEvents

interface DashBoardEvents : BaseEvents {
    val user: User?
}