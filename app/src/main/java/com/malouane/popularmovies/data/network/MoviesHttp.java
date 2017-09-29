package com.malouane.popularmovies.data.network;

import com.malouane.popularmovies.data.MovieDBService;
import com.malouane.popularmovies.data.entity.MovieDetailEntity;
import com.malouane.popularmovies.data.entity.MovieEntity;
import com.malouane.popularmovies.data.entity.MovieReviewEntity;
import com.malouane.popularmovies.data.entity.MovieTrailerEntity;

import javax.inject.Inject;

import io.reactivex.Single;

public class MoviesHttp implements IMoviesHttp {

  MovieDBService mService;

  @Inject public MoviesHttp(MovieDBService mService) {
    this.mService = mService;
  }

  @Override
  public Single<MoviesApiResponse<MovieEntity>> performGetMovies(String listType) {
    return mService.loadMovies(listType);
  }

  @Override public Single<MovieDetailEntity> performGetMovie(int movieId) {
    return mService.loadMovieDetails(movieId);
  }

  @Override
  public Single<MoviesApiResponse<MovieTrailerEntity>> performGetMovieTrailers(int movieId) {
    return mService.getMovieTrailers(movieId);
  }

  @Override
  public Single<MoviesApiResponse<MovieReviewEntity>> performGetMovieReviews(int movieId) {
    return mService.getMovieReviews(movieId);
  }
}
