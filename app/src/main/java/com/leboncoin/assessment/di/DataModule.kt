package com.leboncoin.assessment.di

import androidx.room.Room
import com.leboncoin.assessment.BuildConfig
import com.leboncoin.data.local.AlbumDatabae
import com.leboncoin.data.network.LeBoncoinApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val dataModule = module {
    single { createRetrofitClient() }
    single { createRetrofitService(get()) }

    single {
        Room.databaseBuilder(
            androidApplication(),
            AlbumDatabae::class.java, "albums.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    single { get<AlbumDatabae>().albumsDao() }


}

fun createOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    val httpClient = OkHttpClient.Builder()


    httpClient.connectTimeout(30, TimeUnit.SECONDS)
    httpClient.readTimeout(30, TimeUnit.SECONDS)
    httpClient.addNetworkInterceptor(logging)
    return httpClient.build()

}

fun createRetrofitClient(): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient())
        .build()
}

fun createRetrofitService(retrofit: Retrofit): LeBoncoinApi {
    return retrofit.create(LeBoncoinApi::class.java)
}