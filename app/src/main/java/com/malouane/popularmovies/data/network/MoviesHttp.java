package com.malouane.popularmovies.data.network;

import com.malouane.popularmovies.data.MovieDBService;
import io.reactivex.Single;
import javax.inject.Inject;

public class MoviesHttp implements IMoviesHttp {

  MovieDBService mService;

  @Inject public MoviesHttp(MovieDBService mService) {
    this.mService = mService;
  }

  @Override public Single<MoviesApiResponse> performGetMovies(String listType) {
    return mService.loadMovies(listType);
  }
}
