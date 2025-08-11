package com.elgohry.core.di

import com.elgohry.core.network.HomeApi
import com.elgohry.core.network.SearchApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideHomeApi(@Named("home") retrofit: Retrofit): HomeApi =
        retrofit.create(HomeApi::class.java)

    @Provides @Singleton
    fun provideSearchApi(@Named("search") retrofit: Retrofit): SearchApi =
        retrofit.create(SearchApi::class.java)
}