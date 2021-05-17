package com.billyindrai.architecturecomponent

import androidx.sqlite.db.SimpleSQLiteQuery

object Sorting {
    const val TITLE = "TITLE"
    const val TOP_RATING = "TOP_RATING"
    const val TYPE_MOVIE = "TYPE_MOVIE"
    const val TYPE_TVSHOW = "TYPE_TVSHOW"

    fun getSortedQuery(type: String, filter: String): SimpleSQLiteQuery {
        val simpleQuery: StringBuilder = StringBuilder()
        when (type) {
            TYPE_MOVIE -> simpleQuery.append("SELECT * FROM favMovie ")
            TYPE_TVSHOW -> simpleQuery.append("SELECT * FROM favTv ")
        }

        when (filter) {
            TITLE -> simpleQuery.append("ORDER BY title ASC")
            TOP_RATING -> simpleQuery.append("ORDER BY rating DESC")
        }

        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}