package me.ivulis.jazeps.tmdb.model

import com.squareup.moshi.Json

data class Movies (
    val page: Long,
    @Json(name = "results") val movies: List<Movie>,
    @Json(name = "total_pages") val totalPages: Long,
    @Json(name = "total_results") val totalResults: Long
)

data class Movie (
    val adult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String,
    @Json(name = "genre_ids") val genreIDS: List<Long>,
    val id: Long,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "overview") val description: String,
    val popularity: Double,
    @Json(name = "poster_path") val poster: String,
    @Json(name = "release_date") val releaseDate: String,
    val title: String,
    val video: Boolean,
    @Json(name = "vote_average") val rating: Double,
    @Json(name = "vote_count") val voteCount: Long
)
