package com.example.rikmasterstesttask.data.repository

import com.example.rikmasterstesttask.data.database.AppDatabase
import com.example.rikmasterstesttask.data.mapper.DoorMapper
import com.example.rikmasterstesttask.data.network.service.DoorService
import com.example.rikmasterstesttask.domain.model.DoorDto
import com.example.rikmasterstesttask.domain.repository.DoorRepository
import javax.inject.Inject

class DoorsRepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val doorService: DoorService,
    private val doorMapper: DoorMapper
) : DoorRepository {
    override suspend fun getDoors(): List<DoorDto> {
        return doorService.getDoorResponse().doors.map {
            doorMapper.mapToDto(it)
        }
    }

    override suspend fun getDoorsFromDb(): List<DoorDto> {
        return db.getDoorDao().getDoors().map { doorMapper.mapToDto(it) }
    }

    override suspend fun insertAll(doors: List<DoorDto>) {
        db.getDoorDao().insertAll(doors.map { doorMapper.mapToEntity(it) })
    }

    override suspend fun updateIsFavorite(id: Long, isFavorite: Boolean) {
        db.getDoorDao().updateIsFavorite(id, isFavorite)
    }

    override suspend fun updateName(id: Long, name: String) {
        db.getDoorDao().updateName(id, name)
    }
}