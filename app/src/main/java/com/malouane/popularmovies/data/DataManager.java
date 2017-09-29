package com.malouane.popularmovies.data;

import com.malouane.popularmovies.data.entity.MovieDetailEntity;
import com.malouane.popularmovies.data.entity.MovieEntity;
import com.malouane.popularmovies.data.entity.MovieReviewEntity;
import com.malouane.popularmovies.data.entity.MovieTrailerEntity;
import com.malouane.popularmovies.data.network.IMoviesHttp;
import com.malouane.popularmovies.data.network.MoviesApiResponse;

import io.reactivex.Single;

public class DataManager implements IMoviesHttp {

  IMoviesHttp moviesHttp;

  public DataManager(IMoviesHttp moviesHttp) {
    this.moviesHttp = moviesHttp;
  }

  @Override
  public Single<MoviesApiResponse<MovieEntity>> performGetMovies(String listType) {
    return moviesHttp.performGetMovies(listType);
  }

  @Override public Single<MovieDetailEntity> performGetMovie(int movieId) {
    return moviesHttp.performGetMovie(movieId);
  }

  @Override
  public Single<MoviesApiResponse<MovieTrailerEntity>> performGetMovieTrailers(int movieId) {
    return moviesHttp.performGetMovieTrailers(movieId);
  }

  @Override
  public Single<MoviesApiResponse<MovieReviewEntity>> performGetMovieReviews(int movieId) {
    return moviesHttp.performGetMovieReviews(movieId);
  }
}
