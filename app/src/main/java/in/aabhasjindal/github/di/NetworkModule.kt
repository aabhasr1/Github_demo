package `in`.aabhasjindal.github.di

import `in`.aabhasjindal.github.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val builder = OkHttpClient.Builder()
            .readTimeout(65, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .retryOnConnectionFailure(true)
            .cache(get())
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build()
    }

    single {
        val file = File(androidContext().cacheDir, "okhttp_cache")
        Cache(file, (1000 * 1000 * 100).toLong())
    }

    single {
        HttpLoggingInterceptor { message ->
            // to make sure all the response is shown in logcat (as log messages have length limit)
            val maxLogSize = 1000
            for (i in 0..message.length / maxLogSize) {
                val start = i * maxLogSize
                var end = (i + 1) * maxLogSize
                end = if (end > message.length) message.length else end
                Timber.i(message.substring(start, end))
            }
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}
