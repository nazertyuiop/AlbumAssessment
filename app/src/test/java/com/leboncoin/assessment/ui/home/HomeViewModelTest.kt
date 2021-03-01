package com.leboncoin.assessment.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.leboncoin.assessment.ui.home.HomeViewModel
import com.leboncoin.assessment.utils.MainCoroutineScopeRule
import com.leboncoin.assessment.utils.nextListAlbums
import com.leboncoin.assessment.utils.test
import com.leboncoin.domain.usecase.GetAllAlbumUsecase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import kotlin.random.Random

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val getAllAlbumUsecase = mockk<GetAllAlbumUsecase>()

    private val viewModel = HomeViewModel(getAllAlbumUsecase)

    @Test
    fun `test getAllAlbum Success`() {
        val randomAlbums = Random.nextListAlbums()
        coEvery { getAllAlbumUsecase.exec() } returns randomAlbums

        val stateObserver = viewModel.getListAlbumEvent.test()

        //WHEN
        viewModel.getListAlbum()

        //THEN
        val states = stateObserver.valueHistory()
        assertEquals(2, states.size)
        assertTrue(states[0] is HomeViewModel.AlbumEvent.Loading)
        assertTrue(states[1] is HomeViewModel.AlbumEvent.Success)
        assertEquals(randomAlbums, (states[1] as HomeViewModel.AlbumEvent.Success).items)
    }

    @Test
    fun `test getAllAlbum Error`() {
        coEvery { getAllAlbumUsecase.exec() } throws IOException()

        val stateObserver = viewModel.getListAlbumEvent.test()

        //WHEN
        viewModel.getListAlbum()

        //THEN
        val states = stateObserver.valueHistory()
        assertEquals(2, states.size)
        assertTrue(states[0] is HomeViewModel.AlbumEvent.Loading)
        assertTrue(states[1] is HomeViewModel.AlbumEvent.Error)
        assertTrue((states[1] as HomeViewModel.AlbumEvent.Error).exception is IOException)
    }

}