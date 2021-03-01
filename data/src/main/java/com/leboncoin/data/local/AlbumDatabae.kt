package com.leboncoin.data.local


import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [AlbumEntity::class], version = 1, exportSchema = false)
abstract class AlbumDatabae : RoomDatabase() {
    abstract fun albumsDao(): AlbumDao
}