package com.malouane.popularmovies.data.network;

import io.reactivex.Single;

public interface IMoviesHttp {
  Single<MoviesApiResponse> performGetMovies(String listType);
}
