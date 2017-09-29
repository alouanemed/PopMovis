package com.malouane.popularmovies.data.network;

import com.malouane.popularmovies.data.entity.MovieDetailEntity;
import com.malouane.popularmovies.data.entity.MovieEntity;
import com.malouane.popularmovies.data.entity.MovieReviewEntity;
import com.malouane.popularmovies.data.entity.MovieTrailerEntity;

import io.reactivex.Single;

public interface IMoviesHttp {
  Single<MoviesApiResponse<MovieEntity>> performGetMovies(String listType);

  Single<MovieDetailEntity> performGetMovie(int movieId);

  Single<MoviesApiResponse<MovieTrailerEntity>> performGetMovieTrailers(int movieId);

  Single<MoviesApiResponse<MovieReviewEntity>> performGetMovieReviews(int movieId);
}
