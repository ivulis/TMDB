package me.ivulis.jazeps.tmdb.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Movies(
    val page: Long,
    @Json(name = "results") val movies: List<Movie>,
    @Json(name = "total_pages") val totalPages: Long,
    @Json(name = "total_results") val totalResults: Long
)

data class MovieJson(
    val id: Long,
    @Json(name = "overview") val description: String? = null,
    @Json(name = "poster_path") val poster: String? = null,
    @Json(name = "release_date") val releaseDate: String? = null,
    val runtime: Long? = null,
    val title: String,
    @Json(name = "vote_average") val rating: Double
)

data class Movie(
    val id: String,
    val description: String? = null,
    val poster: String? = null,
    val releaseDate: String? = null,
    val runtime: String? = null,
    val title: String,
    val rating: String
)

class MovieAdapter {
    @FromJson
    fun fromJson(movieJson: MovieJson): Movie {
        return Movie(
            id = movieJson.id.toString(),
            description = movieJson.description,
            poster = "https://image.tmdb.org/t/p/w500${movieJson.poster}",
                releaseDate = when (movieJson.releaseDate) {
                    "" -> movieJson.releaseDate
                    else -> LocalDate.parse(movieJson.releaseDate)
                        .format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"))
                },
            runtime = "${(movieJson.runtime?.div(60))}h ${movieJson.runtime?.rem(60)}m",
            title = movieJson.title,
            rating = String.format("%.1f", movieJson.rating)
        )
    }
}
