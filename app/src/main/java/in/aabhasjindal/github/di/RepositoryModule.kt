package `in`.aabhasjindal.github.di

import `in`.aabhasjindal.github.data.repository.contract.SearchRepository
import `in`.aabhasjindal.github.data.repository.contract.UserRepository
import `in`.aabhasjindal.github.data.repository.implementation.SearchRemoteRepository
import `in`.aabhasjindal.github.data.repository.implementation.UserRemoteRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<SearchRepository> { SearchRemoteRepository(get()) }
    single<UserRepository> { UserRemoteRepository(get()) }
}