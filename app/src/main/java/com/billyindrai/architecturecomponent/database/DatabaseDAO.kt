package com.billyindrai.architecturecomponent.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows
import androidx.paging.DataSource

@Dao
interface DatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShows: TvShows)

    @Query("delete from favMovie where id = :id")
    suspend fun deleteMovie(id: Int)

    @Query("delete from favTv where id = :id")
    suspend fun deleteTv(id: Int)

    @RawQuery(observedEntities = [Movie::class])
    fun getAllMovies(query: SimpleSQLiteQuery): DataSource.Factory<Int, Movie>

    @RawQuery(observedEntities = [TvShows::class])
    fun getAllTvs(query: SimpleSQLiteQuery): DataSource.Factory<Int, TvShows>

    @Query("select * from favMovie where id = :id")
    fun findMovie(id: Int) : LiveData<Movie>

    @Query("select * from favTv where id = :id")
    fun findTv(id: Int) : LiveData<TvShows>
}