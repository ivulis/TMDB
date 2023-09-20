package me.ivulis.jazeps.tmdb.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import me.ivulis.jazeps.tmdb.model.MovieDetails
import me.ivulis.jazeps.tmdb.model.Movies
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "6d86068be3d5562159db9c1da4fd14d4"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Movies

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path(value = "movie_id") movieId: String,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieDetails

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path(value = "movie_id") movieId: String,
        @Query("api_key") apiKey: String = API_KEY
    ): Movies
}

object MovieApi {
    val retrofitService : MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}
