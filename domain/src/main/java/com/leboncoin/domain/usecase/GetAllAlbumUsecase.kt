package com.leboncoin.domain.usecase

import com.leboncoin.domain.AlbumRepository
import com.leboncoin.domain.model.Album
import org.koin.standalone.KoinComponent

class GetAllAlbumUsecase(private val albumRepository: AlbumRepository) : KoinComponent {

    suspend fun exec(): List<Album> = albumRepository.getAllAlbums()
}
