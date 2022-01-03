package com.lakue.androidsampleproject.remote.network

import android.app.Application
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideClubHouseApi(retrofit: Retrofit): PocketApi = retrofit.create()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClient)
            .build()

    @RequiresApi(Build.VERSION_CODES.N)
    @Provides
    @Singleton
    fun provideOkHttpClient(application: Application): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor {

                val original = it.request()

                val locales: LocaleList =
                    application.resources.configuration.locales
                val requestBuilder = original
                    .newBuilder()
//                    .header("CH-Languages", locales.toLanguageTags())
//                    .header("CH-Locale", locales[0].toLanguageTag().replace('-', '_'))
                    .header("Accept", "application/json")
//                    .header("CH-AppBuild", API_BUILD_ID)
//                    .header("CH-AppVersion", API_BUILD_VERSION)
//                    .header("User-Agent", API_UA)
//                    .header("CH-DeviceId", prefManager.deviceId)
                if (userManager.getLoginCheck()) {
                    requestBuilder.header("authorization", "Bearer " + userManager.userToken)
//                        .header("CH-UserID", userManager.userId)
                }

                val newRequest = requestBuilder.build()
                it.proceed(newRequest)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            .build()

    companion object {
        private const val API_BUILD_ID = "304"
        private const val API_BUILD_VERSION = "0.1.28"
        private const val API_UA = "clubhouse/$API_BUILD_ID (iPhone; iOS 13.5.1; Scale/3.00)"
    }
}

