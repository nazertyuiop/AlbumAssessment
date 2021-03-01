package com.leboncoin.assessment.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leboncoin.core.livedata.SingleLiveEvent
import com.leboncoin.domain.model.Album
import com.leboncoin.domain.usecase.GetAlbumDetailUsecase
import kotlinx.coroutines.launch


class AlbumDetailViewModel (private val getAlbumDetailUsecase: GetAlbumDetailUsecase) :
    ViewModel() {


    private val _getAlbumEvent: MutableLiveData<AlbumEvent> = SingleLiveEvent()
    val  getAlbumEvent: LiveData<AlbumEvent> get() = _getAlbumEvent

    fun getDetailAlbum(albumId: Int) {
        _getAlbumEvent.value = AlbumEvent.Loading
        viewModelScope.launch {
            try {
                val album = getAlbumDetailUsecase.exec(id = albumId)
                _getAlbumEvent.value = AlbumEvent.Success(album)
            } catch (e: Exception) {
                _getAlbumEvent.value = AlbumEvent.Error(e)
            }
        }
    }

    sealed class AlbumEvent {
        object Loading : AlbumEvent()
        data class Error(val exception: Exception) : AlbumEvent()
        data class Success(val album: Album) : AlbumEvent()
    }

}