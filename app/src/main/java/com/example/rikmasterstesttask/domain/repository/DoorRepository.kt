package com.example.rikmasterstesttask.domain.repository

import com.example.rikmasterstesttask.domain.model.DoorDto

interface DoorRepository {
    suspend fun getDoors(): List<DoorDto>
    suspend fun getDoorsFromDb(): List<DoorDto>
    suspend fun insertAll(doors: List<DoorDto>)
    suspend fun updateIsFavorite(id: Long, isFavorite: Boolean)
    suspend fun updateName(id: Long, name: String)
}