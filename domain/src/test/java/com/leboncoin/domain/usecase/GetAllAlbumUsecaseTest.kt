package com.leboncoin.domain.usecase

import com.leboncoin.domain.AlbumRepository
import com.leboncoin.domain.nextListAlbums
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException
import kotlin.random.Random

class GetAllAlbumUsecaseTest {


    private val repo = mockk<AlbumRepository>()
    private val useCase = GetAllAlbumUsecase(repo)

    @Test
    fun `test When Success`() = runBlocking {

        //GIVEN
        val list = Random.nextListAlbums()
        coEvery { repo.getAllAlbums() } returns list

        //WHEN
        val result = useCase.exec()

        //THEN
        assertEquals(list, result)
    }


    @Test(expected = IOException::class)
    fun `test When Failure`(): Unit = runBlocking {
        //GIVEN
        coEvery { repo.getAllAlbums() } throws IOException()

        //WHEN
        useCase.exec()
    }
}