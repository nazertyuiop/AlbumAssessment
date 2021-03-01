package com.leboncoin.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlbumDao {


    @Query(value = "SELECT * FROM album_table WHERE id = :albumId")
    suspend fun getAlbum(albumId: Int): AlbumEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(album: AlbumEntity)

    @Query("SELECT * FROM album_table")
    suspend fun getAll(): List<AlbumEntity>

    @Query("SELECT * FROM album_table")
    suspend fun getNextAlbums(): List<AlbumEntity>

}