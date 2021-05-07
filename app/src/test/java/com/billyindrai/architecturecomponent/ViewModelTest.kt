package com.billyindrai.architecturecomponent

import junit.framework.TestCase

class ViewModelTest : TestCase() {
    private lateinit var viewModel: ViewModel

    override fun setUp() {
        viewModel = ViewModel()
    }

    fun testGetMovies() {
        val dataMovie = viewModel.getMovies()
        assertNotNull(dataMovie)
        assertEquals(10, dataMovie.size)
    }

    fun testGetTVShow() {
        val dataTvShows = viewModel.getTVShow()
        assertNotNull(dataTvShows)
        assertEquals(10, dataTvShows.size)
    }
}