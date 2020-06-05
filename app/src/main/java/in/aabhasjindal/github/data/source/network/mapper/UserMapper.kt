package `in`.aabhasjindal.github.data.source.network.mapper

import `in`.aabhasjindal.github.data.model.User
import `in`.aabhasjindal.github.data.source.network.dto.UserDTO

fun UserDTO.mapToUser(): User = User(login!!, id!!.toLong(), avatarUrl!!, true)

fun List<UserDTO>.mapToUsers(): List<User> = this.map { it.mapToUser() }