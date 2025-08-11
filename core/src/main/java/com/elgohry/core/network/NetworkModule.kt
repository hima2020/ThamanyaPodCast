package com.elgohry.core.network

import com.elgohry.core.common.AppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Named
import javax.inject.Singleton
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        coerceInputValues = true
    }

    @Provides @Singleton
    fun provideOkHttp(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        return OkHttpClient.Builder()
            .connectTimeout(NetworkConfig.TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(NetworkConfig.TIMEOUT_SEC, TimeUnit.SECONDS)
            .writeTimeout(NetworkConfig.TIMEOUT_SEC, TimeUnit.SECONDS)
            .addInterceptor(HeadersInterceptor())
            .addInterceptor(logger)
            .build()
    }

    @Provides @Singleton @Named("home")
    fun provideHomeRetrofit(okHttp: OkHttpClient, json: Json): Retrofit =
        Retrofit.Builder()
            .baseUrl(NetworkConfig.BASE_URL)
            .client(okHttp)
            .addConverterFactory(json.asConverterFactory(NetworkConfig.JSON_MEDIA_TYPE))
            .build()

    @Provides @Singleton @Named("search")
    fun provideSearchRetrofit(okHttp: OkHttpClient, json: Json): Retrofit =
        Retrofit.Builder()
            .baseUrl(NetworkConfig.SEARCH_BASE_URL)
            .client(okHttp)
            .addConverterFactory(json.asConverterFactory(NetworkConfig.JSON_MEDIA_TYPE))
            .build()

    @Provides @Singleton
    fun provideDispatchers(): AppDispatchers = AppDispatchers(
        io = Dispatchers.IO,
        default = Dispatchers.Default,
        main = Dispatchers.Main
    )
}