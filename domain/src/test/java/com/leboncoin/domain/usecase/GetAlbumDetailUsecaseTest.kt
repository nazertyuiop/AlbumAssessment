package com.leboncoin.domain.usecase

import com.leboncoin.domain.AlbumRepository
import com.leboncoin.domain.nextAlbum
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class GetAlbumDetailUsecaseTest {


    private val repo = mockk<AlbumRepository>()
    private val useCase = GetAlbumDetailUsecase(repo)

    @Test
    fun `test When Success`() = runBlocking {

        val idAlbum = nextInt()
        //GIVEN
        val random = Random.nextAlbum()
        coEvery { repo.getAlbumDetail(idAlbum) } returns random

        //WHEN
        val result = useCase.exec(idAlbum)

        //THEN
        assertEquals(random, result)
    }


    @Test(expected = IOException::class)
    fun `test When Failure`(): Unit = runBlocking {
        val idAlbum = nextInt()
        //GIVEN
        coEvery { repo.getAlbumDetail(idAlbum) } throws IOException()

        //WHEN
        useCase.exec(idAlbum)
    }
}