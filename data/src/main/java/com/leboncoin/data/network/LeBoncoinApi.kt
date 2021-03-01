package com.leboncoin.data.network

import retrofit2.http.GET


interface LeBoncoinApi {
    
    @GET("/img/shared/technical-test.json")
    suspend fun getAllAlbums(): List<AlbumResponse>

}