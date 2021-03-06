package com.leboncoin.domain.usecase

import com.leboncoin.domain.AlbumRepository
import com.leboncoin.domain.model.Album
import org.koin.standalone.KoinComponent

class GetAlbumDetailUsecase(private val albumRepository: AlbumRepository) : KoinComponent {

    suspend fun exec(id: Int): Album = albumRepository.getAlbumDetail(id)
}