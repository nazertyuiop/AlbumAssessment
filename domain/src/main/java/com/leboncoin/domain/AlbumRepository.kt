package com.leboncoin.domain

import com.leboncoin.domain.model.Album


interface AlbumRepository
{

    suspend fun getAllAlbums(): List<Album>

    suspend fun getAlbumDetail(id: Int): Album

    suspend fun refreshAlbumList()

}