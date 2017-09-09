package com.malouane.popularmovies.data;

import com.malouane.popularmovies.data.network.IMoviesHttp;
import com.malouane.popularmovies.data.network.MoviesApiResponse;
import io.reactivex.Single;

public class DataManager implements IMoviesHttp {

  IMoviesHttp moviesHttp;

  public DataManager(IMoviesHttp moviesHttp) {
    this.moviesHttp = moviesHttp;
  }

  @Override public Single<MoviesApiResponse> performGetMovies(String listType) {
    return moviesHttp.performGetMovies(listType);
  }
}
