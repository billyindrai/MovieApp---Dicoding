package com.billyindrai.architecturecomponent.repository

import com.billyindrai.architecturecomponent.data.Genre
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows
import java.util.*

object Dummy {
    fun generateDummyMovies(): List<Movie> {

        val movies = ArrayList<Movie>()

        movies.add(
            Movie(460465,
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "Mortal Kombat",
                7.8f,
                "2021-04-07",
                110,
                genres = listOf(
                    Genre(
                        id = 28,
                        name = "Action"
                    ),
                    Genre(
                        id = 14,
                        name = "Fantasy"
                    ),
                    Genre(
                        id = 12,
                        name = "Adventure"
                    ),
                    Genre(
                        id = 878,
                        name = "Science Fiction"
                    )
                ),
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe."
            )
        )
        movies.add(
            Movie(399566,
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "Godzilla vs. Kong",
                8.1f,
                "2021-03-24",
                113,
                genres = listOf(
                    Genre(
                        id = 878,
                        name = "Science Fiction"
                    ),
                    Genre(
                        id = 28,
                        name = "Action"
                    ),
                    Genre(
                        id = 18,
                        name = "Drama"
                    )
                ),
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
            )
        )
        return movies
    }

    fun generateDummyTVShow(): List<TvShows> {

        val tvShow = ArrayList<TvShows>()

        tvShow.add(
            TvShows(
                95557,
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "Invincible",
                8.9f,
                "2021-03-26",
                8,
                1,
                genres = listOf(
                    Genre(
                        id = 16,
                        name = "Animation"
                    ),
                    Genre(
                        id = 10759,
                        name = "Action & Adventure"
                    ),
                    Genre(
                        id = 18,
                        name = "Drama"
                    ),
                    Genre(
                        id = 10765,
                        name = "Sci-Fi & Fantasy"
                    )
                ),
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage."
            )
        )
        tvShow.add(
            TvShows(
                88396,
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "The Falcon and the Winter Soldier",
                7.9f,
                "2021-03-19",
                6,
                1,
                genres = listOf(
                    Genre(
                        id = 10765,
                        name = "Sci-Fi & Fantasy"
                    ),
                    Genre(
                        id = 10759,
                        name = "Action & Adventure"
                    ),
                    Genre(
                        id = 18,
                        name = "Drama"
                    ),
                    Genre(
                        id = 10768,
                        name = "War & Politics"
                    )
                ),
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience."
            )
        )
        return tvShow
    }
}