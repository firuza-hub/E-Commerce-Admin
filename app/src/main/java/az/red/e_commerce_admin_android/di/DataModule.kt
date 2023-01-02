package az.red.e_commerce_admin_android.di

import az.red.e_commerce_admin_android.data.remote.HeaderInterceptor
import az.red.e_commerce_admin_android.data.remote.NetworkRepository
import az.red.e_commerce_admin_android.data.remote.NetworkService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    single {
        val interceptor = HeaderInterceptor()
        val client = OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .callTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(interceptor)

        if (BuildConfig.DEBUG) {
            val logger =
                HttpLoggingInterceptor().setLevel(level = HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(logger)
        }
        client.build()
    }

    factory<NetworkService> { get<Retrofit>().create(NetworkService::class.java) }

    single {
        Retrofit.Builder()
            .baseUrl(getProperty("base_url"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    factory { NetworkRepository(get()) }
}