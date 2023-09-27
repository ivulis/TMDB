package me.ivulis.jazeps.tmdb.model

import com.squareup.moshi.Json

data class MovieDetails (
    val id: Long,
    @Json(name = "overview") val description: String? = null,
    @Json(name = "poster_path") val poster: String? = null,
    @Json(name = "release_date") val releaseDate: String? = null,
    val runtime: Long? = null,
    val title: String,
    val video: Boolean,
    @Json(name = "vote_average") val rating: Double,
)
