package com.malouane.popularmovies.data;

import com.malouane.popularmovies.data.entity.MovieDetailEntity;
import com.malouane.popularmovies.data.entity.MovieEntity;
import com.malouane.popularmovies.data.entity.MovieReviewEntity;
import com.malouane.popularmovies.data.entity.MovieTrailerEntity;
import com.malouane.popularmovies.data.network.MoviesApiResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieDBService {
  @GET("movie/{listType}")
  Single<MoviesApiResponse<MovieEntity>> loadMovies(@Path("listType") String listType);

  @GET("movie/{movie_id}") Single<MovieDetailEntity> loadMovieDetails(
          @Path("movie_id") int movieId);

  @GET("movie/{movie_id}/videos")
  Single<MoviesApiResponse<MovieTrailerEntity>> getMovieTrailers(
          @Path("movie_id") int movieId);

  @GET("movie/{movie_id}/reviews")
  Single<MoviesApiResponse<MovieReviewEntity>> getMovieReviews(
          @Path("movie_id") int movieId);
}