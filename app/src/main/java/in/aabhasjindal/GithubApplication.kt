package `in`.aabhasjindal

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class GithubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
        }
    }
}