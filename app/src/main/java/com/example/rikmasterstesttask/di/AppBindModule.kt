package com.example.rikmasterstesttask.di

import com.example.rikmasterstesttask.data.repository.CameraRepositoryImpl
import com.example.rikmasterstesttask.data.repository.DoorsRepositoryImpl
import com.example.rikmasterstesttask.domain.repository.CameraRepository
import com.example.rikmasterstesttask.domain.repository.DoorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindModule {

    @Singleton
    @Binds
    abstract fun bindCameraRepository(
        cameraRepositoryImpl: CameraRepositoryImpl
    ): CameraRepository

    @Singleton
    @Binds
    abstract fun bindDoorRepository(
        doorRepositoryImpl: DoorsRepositoryImpl
    ): DoorRepository
}