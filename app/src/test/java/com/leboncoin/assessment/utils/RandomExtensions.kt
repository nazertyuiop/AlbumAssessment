package com.leboncoin.assessment.utils

import com.leboncoin.domain.model.Album
import kotlin.random.Random


internal fun Random.nextListAlbums() =
    generateSequence { nextAlbum() }.take(nextInt(6)).toList()



fun Random.nextAlbum() = Album(
    nextInt(),
    nextInt(),
    nextInt().toString(),
    nextInt().toString(),
    nextInt().toString(),
)

