package com.billyindrai.architecturecomponent

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows
import com.billyindrai.architecturecomponent.repository.Dummy
import com.billyindrai.architecturecomponent.repository.FakeRepo
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest{
    private lateinit var viewModel: DetailViewModel
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: FakeRepo

    private val idMovie = 332562
    private val idTv = 95557

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun testSetSelectedData() {
        viewModel.setSelectedData(idMovie)
        assertEquals(idMovie, viewModel.id)
    }

    @Test
    fun testGetDataMovie() {
        viewModel.setSelectedData(idMovie)

        val dummyMovie = Dummy.generateDummyMovies()[0]
        val movies = MutableLiveData<Movie>()
        movies.value = dummyMovie

        Mockito.`when`(repository.getDetailMovie(idMovie)).thenReturn(movies)
        val dataMovie = viewModel.getDataMovie().value
        Mockito.verify(repository).getDetailMovie(idMovie)

        Assert.assertNotNull(dataMovie)

        assertEquals(dummyMovie.id, dataMovie?.id)
        assertEquals(dummyMovie.poster, dataMovie?.poster)
        assertEquals(dummyMovie.title, dataMovie?.title)
        assertEquals(dummyMovie.rating, dataMovie?.rating)
        assertEquals(dummyMovie.date, dataMovie?.date)
        assertEquals(dummyMovie.duration, dataMovie?.duration)
        assertEquals(dummyMovie.genres, dataMovie?.genres)
        assertEquals(dummyMovie.description,dataMovie?.description)
    }

    @Test
    fun testGetDataTvShows() {
        viewModel.setSelectedData(idTv)

        val dummyTvShow = Dummy.generateDummyTVShow()[0]
        val tvShow = MutableLiveData<TvShows>()
        tvShow.value = dummyTvShow

        Mockito.`when`(repository.getDetailTvShow(idTv)).thenReturn(tvShow)
        val dataTvShow = viewModel.getDataTvShows().value
        Mockito.verify(repository).getDetailTvShow(idTv)

        Assert.assertNotNull(dataTvShow)

        assertEquals(dummyTvShow.id, dataTvShow?.id)
        assertEquals(dummyTvShow.poster, dataTvShow?.poster)
        assertEquals(dummyTvShow.title, dataTvShow?.title)
        assertEquals(dummyTvShow.rating, dataTvShow?.rating)
        assertEquals(dummyTvShow.date, dataTvShow?.date)
        assertEquals(dummyTvShow.episodes, dataTvShow?.episodes)
        assertEquals(dummyTvShow.seasons, dataTvShow?.seasons)
        assertEquals(dummyTvShow.genres, dataTvShow?.genres)
        assertEquals(dummyTvShow.description,dataTvShow?.description)
    }
}