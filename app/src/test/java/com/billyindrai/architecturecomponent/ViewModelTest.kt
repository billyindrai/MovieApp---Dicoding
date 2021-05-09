package com.billyindrai.architecturecomponent

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows
import com.billyindrai.architecturecomponent.repository.Dummy
import com.billyindrai.architecturecomponent.repository.FakeRepo
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ViewModelTest {
    private lateinit var viewModel: ViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observerMovie: Observer<List<Movie>>
    @Mock
    private lateinit var observerTVShow: Observer<List<TvShows>>
    @Mock
    private lateinit var repository: FakeRepo

    @Before
    fun setUp() {
        viewModel = ViewModel(repository)
    }

    @Test
    fun testGetMovies() {
        val dummyMovie = Dummy.generateDummyMovies()
        val movies = MutableLiveData<List<Movie>>()
        movies.value = dummyMovie

        Mockito.`when`(repository.getPopularMovie()).thenReturn(movies as LiveData<ArrayList<Movie>>)
        val movieEntities = viewModel.getMovies().value
        Mockito.verify(repository).getPopularMovie()
        Assert.assertNotNull(movies)
        Assert.assertEquals(2, movieEntities?.size)

        viewModel.getMovies().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun testGetTVShow() {
        val dummyTvShow = Dummy.generateDummyTVShow()
        val tvShow = MutableLiveData<List<TvShows>>()
        tvShow.value = dummyTvShow

        Mockito.`when`(repository.getPopularTvShow()).thenReturn(tvShow as LiveData<ArrayList<TvShows>>)
        val tvEntities = viewModel.getTVShow().value
        Mockito.verify(repository).getPopularTvShow()
        Assert.assertNotNull(tvShow)
        Assert.assertEquals(2, tvEntities?.size)

        viewModel.getTVShow().observeForever(observerTVShow)
        Mockito.verify(observerTVShow).onChanged(dummyTvShow)
    }
}