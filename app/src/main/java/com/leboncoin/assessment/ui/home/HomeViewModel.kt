package com.leboncoin.assessment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leboncoin.core.livedata.SingleLiveEvent
import com.leboncoin.domain.model.Album
import com.leboncoin.domain.usecase.GetAllAlbumUsecase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAllAlbumUsecase: GetAllAlbumUsecase
) : ViewModel() {


    private val _getListAlbumEvent: MutableLiveData<AlbumEvent> = SingleLiveEvent()
    val getListAlbumEvent: LiveData<AlbumEvent> get() = _getListAlbumEvent

    fun getListAlbum() {
        _getListAlbumEvent.value = AlbumEvent.Loading
        viewModelScope.launch {
            try {
                val lstAlbums = getAllAlbumUsecase.exec()
                _getListAlbumEvent.value = AlbumEvent.Success(lstAlbums)
            } catch (e: Exception) {
                _getListAlbumEvent.value = AlbumEvent.Error(e)
            }
        }
    }


    sealed class AlbumEvent {
        object Loading : AlbumEvent()
        data class Error(val exception: Exception) : AlbumEvent()
        data class Success(val items: List<Album>) : AlbumEvent()
    }

}