package `in`.aabhasjindal.github.di

import `in`.aabhasjindal.github.data.source.network.api.GithubApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single {
        get<Retrofit>().create(GithubApi::class.java)
    }
}