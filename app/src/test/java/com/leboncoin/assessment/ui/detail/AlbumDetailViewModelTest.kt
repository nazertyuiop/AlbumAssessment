package com.leboncoin.assessment.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.leboncoin.assessment.utils.MainCoroutineScopeRule
import com.leboncoin.assessment.utils.nextAlbum
import com.leboncoin.assessment.utils.test
import com.leboncoin.domain.usecase.GetAlbumDetailUsecase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import kotlin.random.Random
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

@ExperimentalCoroutinesApi
class AlbumDetailViewModelTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val getAlbumDetailUsecase = mockk<GetAlbumDetailUsecase>()

    private val viewModel = AlbumDetailViewModel(getAlbumDetailUsecase)


    @Test
    fun `test getAlbumDetails Success`() {
        val randomAlbum = Random.nextAlbum()
        coEvery { getAlbumDetailUsecase.exec(any()) } returns randomAlbum

        val stateObserver = viewModel.getAlbumEvent.test()

        //WHEN
        viewModel.getDetailAlbum(Random.nextInt())

        //THEN
        val states = stateObserver.valueHistory()
        assertEquals(2, states.size)
        assertTrue(states[0] is AlbumDetailViewModel.AlbumEvent.Loading)
        assertTrue(states[1] is AlbumDetailViewModel.AlbumEvent.Success)

        assertEquals(randomAlbum, (states[1] as AlbumDetailViewModel.AlbumEvent.Success).album)
    }

    @Test
    fun `test getAlbumDetails Error`() {
        coEvery { getAlbumDetailUsecase.exec(any()) } throws IOException()

        val stateObserver = viewModel.getAlbumEvent.test()

        //WHEN
        viewModel.getDetailAlbum(Random.nextInt())

        //THEN
        val states = stateObserver.valueHistory()
        assertEquals(2, states.size)
        assertTrue(states[0] is AlbumDetailViewModel.AlbumEvent.Loading)
        assertTrue(states[1] is AlbumDetailViewModel.AlbumEvent.Error)
        assertTrue((states[1] as AlbumDetailViewModel.AlbumEvent.Error).exception is IOException)
    }



}