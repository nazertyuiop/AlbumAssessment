package com.leboncoin.data

import com.leboncoin.data.local.AlbumEntity
import com.leboncoin.data.network.AlbumResponse
import org.junit.Assert
import org.junit.Test
import kotlin.random.Random


class MappingTest {

    @Test(expected = IllegalArgumentException::class)
    fun `test toModel ThrowException when idAlbum is Null`() {
        //given
        val response = Random.nextAlbumResponse().copy(albumId = null)

        //when
        response.toEntity()
    }


    @Test
    fun `test toModel when success`() {

        //given
        val response = Random.nextAlbumResponse()

        val expected = AlbumEntity(
            id = response.id,
            albumId = response.albumId,
            thumbnailUrl = response.thumbnailUrl,
            url = response.url,
            title = response.title
        )

        //when
        val result = response.toEntity()

        Assert.assertEquals(expected, result)
    }

}



private fun Random.nextAlbumResponse(): AlbumResponse {
    return AlbumResponse(
        id = nextInt(),
        albumId = nextInt(),
        title = nextInt().toString(),
        url = nextInt().toString(),
        thumbnailUrl = nextInt().toString()
    )
}

private fun Random.nextAlbumEntity(): AlbumEntity {
    return AlbumEntity(
        id = nextInt(),
        albumId = nextInt(),
        title = nextInt().toString(),
        url = nextInt().toString(),
        thumbnailUrl = nextInt().toString()
    )
}