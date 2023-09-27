package me.ivulis.jazeps.tmdb.model

import com.squareup.moshi.Json

data class Movies (
    val page: Long,
    @Json(name = "results") val movies: List<Movie>,
    @Json(name = "total_pages") val totalPages: Long,
    @Json(name = "total_results") val totalResults: Long
)

data class Movie (
    val id: Long,
    @Json(name = "overview") val description: String? = null,
    @Json(name = "poster_path") val poster: String? = null,
    @Json(name = "release_date") val releaseDate: String? = null,
    val title: String,
    val video: Boolean,
    @Json(name = "vote_average") val rating: Double,
)
