package com.malouane.popularmovies.data;

import com.malouane.popularmovies.data.network.MoviesApiResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieDBService {
  @GET("movie/{listType}") Single<MoviesApiResponse> loadMovies(@Path("listType") String listType);
}