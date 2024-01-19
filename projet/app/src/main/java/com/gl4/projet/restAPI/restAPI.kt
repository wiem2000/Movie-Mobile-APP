package com.gl4.projet.restAPI

import com.gl4.projet.models.genre_models.GenreResponse
import com.gl4.projet.models.movie_detail_models.MovieDetailResponse
import com.gl4.projet.models.movie_models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestAPI {
    @GET("genre/movie/list?api_key=04835f4bd3df9fff98c372bc79866096")
    fun getGenres() : Call<GenreResponse>

    @GET("discover/movie?api_key=04835f4bd3df9fff98c372bc79866096")
    fun getMovieByGenre(@Query("with_genres") genre_id : String, @Query("page") page: Int) : Call<MovieResponse>

    @GET("movie/{movie_id}?&api_key=04835f4bd3df9fff98c372bc79866096")
    fun getMovieById(@Path("movie_id") movie_id : String): Call<MovieDetailResponse>

    @GET("search/movie?api_key=04835f4bd3df9fff98c372bc79866096")
    fun searchMovie(@Query("query") query : String, @Query("page") page: Int) : Call<MovieResponse>

}