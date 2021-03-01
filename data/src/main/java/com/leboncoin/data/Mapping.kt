package com.leboncoin.data

import com.leboncoin.data.local.AlbumEntity
import com.leboncoin.data.network.AlbumResponse
import com.leboncoin.domain.model.Album


fun AlbumEntity.toModel(): Album {
    return Album(
        id = id,
        albumId = albumId,
        title = title,
        imgUrl = url,
        thumbnailUrl = thumbnailUrl
    )
}


fun AlbumResponse.toEntity(): AlbumEntity {
    return AlbumEntity(
        id = id,
        albumId = albumId,
        title = title,
        url = if (url?.contains("http") == true) url else "https://$url",
        thumbnailUrl = thumbnailUrl
    )
}
