package com.leboncoin.data

import android.annotation.SuppressLint
import androidx.paging.DataSource
import com.leboncoin.data.local.AlbumDao
import com.leboncoin.data.local.AlbumEntity
import com.leboncoin.data.network.LeBoncoinApi
import com.leboncoin.domain.AlbumRepository
import com.leboncoin.domain.model.Album


class AlbumRepositoryImp(
    private val AlbumDao: AlbumDao,
    private val leBoncoinApi: LeBoncoinApi
) : AlbumRepository {


    override suspend fun getAllAlbums(): List<Album> {
        val lstAlbum = AlbumDao.getNextAlbums()
        if (lstAlbum.isEmpty()) {
            refreshAlbumList()
        }
        return AlbumDao.getNextAlbums().map { it.toModel() }
    }

    override suspend fun getAlbumDetail(albumId: Int): Album {
        return AlbumDao.getAlbum(albumId = albumId).toModel()
    }

    @SuppressLint("CheckResult")
    override suspend fun refreshAlbumList() {

        getListAlbumsFromWS().forEach {
            insert(it.toEntity())
        }
    }

    private suspend fun getListAlbumsFromWS() = leBoncoinApi.getAllAlbums()

    private suspend fun insert(album: AlbumEntity) {
        AlbumDao.insert(album)
    }

}