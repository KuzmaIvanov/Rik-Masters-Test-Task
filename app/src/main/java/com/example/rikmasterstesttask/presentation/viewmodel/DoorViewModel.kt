package com.example.rikmasterstesttask.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rikmasterstesttask.di.IoDispatcher
import com.example.rikmasterstesttask.domain.model.DoorDto
import com.example.rikmasterstesttask.domain.repository.DoorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoorViewModel @Inject constructor(
    private val doorRepository: DoorRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _doorUiState = MutableLiveData<Result<List<DoorDto>>>(Result.success(emptyList()))
    val doorUiState get() = _doorUiState

    private val _refreshing = MutableLiveData(false)
    val refreshing get() = _refreshing

    init {
        getDoorsFromDb()
    }

    fun getDoors() {
        viewModelScope.launch(ioDispatcher) {
            _refreshing.postValue(true)
            kotlin.runCatching {
                doorRepository.getDoors()
            }.onFailure {
                _doorUiState.postValue(Result.failure(it))
            }.onSuccess {
                _doorUiState.postValue(Result.success(it))
                insertAll(it)
            }
            _refreshing.postValue(false)
        }
    }

    fun updateIsFavorite(id: Long, isFavorite: Boolean) {
        viewModelScope.launch(ioDispatcher) {
            doorRepository.updateIsFavorite(id, isFavorite)
        }
    }

    fun updateName(id: Long, name: String) {
        viewModelScope.launch(ioDispatcher) {
            doorRepository.updateName(id, name)
        }
    }

    private fun getDoorsFromDb() {
        viewModelScope.launch(ioDispatcher) {
            _refreshing.postValue(true)
            kotlin.runCatching {
                doorRepository.getDoorsFromDb()
            }.onFailure {
                _doorUiState.postValue(Result.failure(it))
            }.onSuccess {
                if (it.isEmpty()) {
                    getDoors()
                    insertAll(_doorUiState.value!!.getOrThrow())
                } else {
                    _doorUiState.postValue(Result.success(it))
                }
            }
            _refreshing.postValue(false)
        }
    }

    private fun insertAll(doors: List<DoorDto>) {
        viewModelScope.launch(ioDispatcher) {
            doorRepository.insertAll(doors)
        }
    }
}