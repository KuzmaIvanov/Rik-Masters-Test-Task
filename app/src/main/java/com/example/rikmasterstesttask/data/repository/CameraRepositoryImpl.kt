package com.example.rikmasterstesttask.data.repository

import com.example.rikmasterstesttask.data.database.AppDatabase
import com.example.rikmasterstesttask.data.mapper.CameraMapper
import com.example.rikmasterstesttask.data.network.service.CameraService
import com.example.rikmasterstesttask.domain.model.CameraDto
import com.example.rikmasterstesttask.domain.repository.CameraRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CameraRepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val cameraService: CameraService,
    private val cameraMapper: CameraMapper,
) : CameraRepository {
    override suspend fun getCameras(): List<CameraDto> {
        return cameraService.getCameraResponse().data.cameras.map {
            cameraMapper.mapToDto(it)
        }
    }

    override suspend fun getCamerasFromDb(): List<CameraDto> {
        return db.getCameraDao().getCameras().map {
            cameraMapper.mapToDto(it)
        }
    }

    override suspend fun insertAll(cameras: List<CameraDto>) {
        db.getCameraDao().insertAll(cameras.map { cameraMapper.mapToEntity(it) })
    }

    override suspend fun update(id: Long, isFavorite: Boolean) {
        db.getCameraDao().update(id, isFavorite)
    }
}