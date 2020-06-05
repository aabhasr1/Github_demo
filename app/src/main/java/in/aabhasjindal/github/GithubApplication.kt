package `in`.aabhasjindal.github

import `in`.aabhasjindal.github.di.apiModule
import `in`.aabhasjindal.github.di.networkModule
import `in`.aabhasjindal.github.di.repositoryModule
import `in`.aabhasjindal.github.di.viewModelModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


class GithubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(applicationContext)
            modules(apiModule)
            modules(networkModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }
    }
}