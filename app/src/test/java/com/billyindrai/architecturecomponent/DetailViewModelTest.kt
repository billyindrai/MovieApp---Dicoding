package com.billyindrai.architecturecomponent

import junit.framework.TestCase

class DetailViewModelTest : TestCase() {
    private lateinit var viewModel: DetailViewModel
    private val dummyDataMovie = Dummy.dummyMovies()[0]
    private val dummyDataTv = Dummy.dummyTvShows()[0]
    private val idMovie = dummyDataMovie.id
    private val idTv = dummyDataMovie.id

    public override fun setUp() {
        super.setUp()
        viewModel = DetailViewModel()
        if(dummyDataMovie.id == idMovie) {
            viewModel.setSelectedData(idMovie)
        }else{
            viewModel.setSelectedData(idTv)
        }
    }

    fun testSetSelectedData() {}

    fun testGetDataMovie() {
        viewModel.setSelectedData(dummyDataMovie.id)
        val data = viewModel.getDataMovie()
        assertNotNull(data)
            assertEquals(dummyDataMovie.id, data?.id)
            assertEquals(dummyDataMovie.poster, data?.poster)
            assertEquals(dummyDataMovie.title, data?.title)
            assertEquals(dummyDataMovie.rating, data?.rating)
            assertEquals(dummyDataMovie.date, data?.date)
            assertEquals(dummyDataMovie.duration, data?.duration)
            assertEquals(dummyDataMovie.genre, data?.genre)
            assertEquals(dummyDataMovie.description, data?.description)

    }


    fun testGetDataTvShows() {
        viewModel.setSelectedData(dummyDataTv.id)
        val data = viewModel.getDataTvShows()
        assertNotNull(data)
            assertEquals(dummyDataTv.id, data?.id)
            assertEquals(dummyDataTv.poster, data?.poster)
            assertEquals(dummyDataTv.title, data?.title)
            assertEquals(dummyDataTv.rating, data?.rating)
            assertEquals(dummyDataTv.date, data?.date)
            assertEquals(dummyDataTv.duration, data?.duration)
            assertEquals(dummyDataTv.genre, data?.genre)
            assertEquals(dummyDataTv.description, data?.description)
    }
}