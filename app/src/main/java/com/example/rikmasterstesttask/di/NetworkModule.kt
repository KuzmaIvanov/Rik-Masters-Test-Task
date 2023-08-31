package com.example.rikmasterstesttask.di

import com.example.rikmasterstesttask.BuildConfig
import com.example.rikmasterstesttask.data.network.service.CameraService
import com.example.rikmasterstesttask.data.network.service.DoorService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideDoorService(
        retrofit: Retrofit
    ): DoorService {
        return retrofit.create(DoorService::class.java)
    }

    @Singleton
    @Provides
    fun provideCameraService(
        retrofit: Retrofit
    ): CameraService {
        return retrofit.create(CameraService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(
        gson: Gson
    ): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }
}