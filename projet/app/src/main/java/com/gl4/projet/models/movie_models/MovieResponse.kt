package com.gl4.projet.models.movie_models

data class MovieResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)