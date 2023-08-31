package com.example.rikmasterstesttask.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rikmasterstesttask.di.IoDispatcher
import com.example.rikmasterstesttask.domain.model.CameraDto
import com.example.rikmasterstesttask.domain.repository.CameraRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val cameraRepository: CameraRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _camerasUiState =
        MutableLiveData<Result<List<CameraDto>>>(Result.success(emptyList()))
    val camerasUiState get() = _camerasUiState

    private val _refreshing = MutableLiveData(false)
    val refreshing get() = _refreshing

    init {
        getCamerasFromDb()
    }

    fun getCameras() {
        viewModelScope.launch(ioDispatcher) {
            _refreshing.postValue(true)
            kotlin.runCatching {
                cameraRepository.getCameras()
            }.onFailure {
                _camerasUiState.postValue(Result.failure(it))
            }.onSuccess {
                _camerasUiState.postValue(Result.success(it))
                insertAll(it)
            }
            _refreshing.postValue(false)
        }
    }

    fun update(id: Long, isFavorite: Boolean) {
        viewModelScope.launch(ioDispatcher) {
            cameraRepository.update(id, isFavorite)
        }
    }

    private fun getCamerasFromDb() {
        viewModelScope.launch(ioDispatcher) {
            _refreshing.postValue(true)
            kotlin.runCatching {
                cameraRepository.getCamerasFromDb()
            }.onFailure {
                _camerasUiState.postValue(Result.failure(it))
            }.onSuccess {
                if (it.isEmpty()) {
                    getCameras()
                    insertAll(_camerasUiState.value!!.getOrThrow())
                } else {
                    _camerasUiState.postValue(Result.success(it))
                }
            }
            _refreshing.postValue(false)
        }
    }

    private fun insertAll(cameras: List<CameraDto>) {
        viewModelScope.launch(ioDispatcher) {
            cameraRepository.insertAll(cameras)
        }
    }
}