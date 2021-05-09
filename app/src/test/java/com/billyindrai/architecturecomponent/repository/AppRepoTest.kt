package com.billyindrai.architecturecomponent.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.billyindrai.architecturecomponent.LiveDataTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class AppRepoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(FakeData::class.java)
    private val repository = FakeRepo(remote)

    private val moviesResponses = Dummy.generateDummyMovies()
    private val movieContent = moviesResponses[0]
    private val tvResponses = Dummy.generateDummyTVShow()
    private val tvContent = tvResponses[0]

    @Test
    fun getPopularMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as FakeData.LoadMoviesCallback)
                .onAllMoviesReceived(moviesResponses)
            null
        }.`when`(remote).getPopMovie(any())
        val moviesEntities = LiveDataTest.getValue(repository.getPopularMovie())
        verify(remote).getPopMovie(any())
        Assert.assertNotNull(moviesEntities)
        assertEquals(moviesResponses.size.toLong(), moviesEntities.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as FakeData.LoadMovieContentCallback)
                .onContentReceived(movieContent)
            null
        }.`when`(remote).getMovieDet(eq(movieContent.id), any())

        val movieEntitiesContent = LiveDataTest.getValue(repository.getDetailMovie(movieContent.id))

        verify(remote)
            .getMovieDet(eq(movieContent.id), any())

        Assert.assertNotNull(movieEntitiesContent)
        assertEquals(movieContent.id, movieEntitiesContent.id)
    }

    @Test
    fun getPopularTVs() {
        doAnswer { invocation ->
            (invocation.arguments[0] as FakeData.LoadTVShowCallback)
                .onAllTVShowReceived(tvResponses)
            null
        }.`when`(remote).getPopTv(any())
        val tvEntities = LiveDataTest.getValue(repository.getPopularTvShow())
        verify(remote).getPopTv(any())
        Assert.assertNotNull(tvEntities)
        assertEquals(tvResponses.size.toLong(), tvEntities.size.toLong())
    }

    @Test
    fun getDetailTV() {
        doAnswer { invocation ->
            (invocation.arguments[1] as FakeData.LoadTVContentCallback)
                .onContentReceived(tvContent)
            null
        }.`when`(remote).getTvDet(eq(tvContent.id), any())

        val tvEntitiesContent = LiveDataTest.getValue(repository.getDetailTvShow(tvContent.id))

        verify(remote)
            .getTvDet(eq(tvContent.id), any())

        Assert.assertNotNull(tvEntitiesContent)
        assertEquals(tvContent.id, tvEntitiesContent.id)
    }
}