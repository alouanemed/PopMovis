package com.malouane.popularmovies.data.network;

import com.malouane.popularmovies.data.MovieDetailEntity;
import io.reactivex.Single;

public interface IMoviesHttp {
  Single<MoviesApiResponse> performGetMovies(String listType);

  Single<MovieDetailEntity> performGetMovie(int movieId);
}
