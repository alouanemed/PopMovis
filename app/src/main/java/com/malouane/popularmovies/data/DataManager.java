package com.malouane.popularmovies.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.malouane.popularmovies.data.database.FavoritesMovieContract;
import com.malouane.popularmovies.data.entity.MovieDetailEntity;
import com.malouane.popularmovies.data.entity.MovieEntity;
import com.malouane.popularmovies.data.entity.MovieReviewEntity;
import com.malouane.popularmovies.data.entity.MovieTrailerEntity;
import com.malouane.popularmovies.data.network.IMoviesHttp;
import com.malouane.popularmovies.data.network.MoviesApiResponse;
import com.malouane.popularmovies.utils.PopMovisUtils;

import io.reactivex.Single;

public class DataManager implements IMoviesHttp {

  IMoviesHttp moviesHttp;
  Application app;

  public DataManager(IMoviesHttp moviesHttp, @NonNull final Application app) {
    this.moviesHttp = moviesHttp;
    this.app = app;
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

  @NonNull
  public LiveData<Boolean> query(@NonNull final Integer movieId) {

    return new LiveData<Boolean>() {

      @Override
      protected void onActive() {
        super.onActive();
        final String selectionClause = FavoritesMovieContract.FavoritesMovies.COLUMN_MOVIE_ID + " = ?";
        final String[] selectionArgs = {movieId.toString()};
        final Cursor cursor = app
                .getContentResolver()
                .query(PopMovisUtils.itemUri(movieId),
                        new String[]{FavoritesMovieContract.FavoritesMovies.COLUMN_MOVIE_ID, FavoritesMovieContract.FavoritesMovies.COLUMN_TITLE},
                        selectionClause,
                        selectionArgs,
                        null
                );
        try {
          if (cursor != null) {
            if (cursor.getCount() == 1) {
              cursor.moveToFirst();
              postValue(cursor.getString(cursor.getColumnIndex(FavoritesMovieContract.FavoritesMovies.COLUMN_MOVIE_ID)).equals(movieId.toString()));

            } else {
              postValue(false);
            }
          }
        } finally {
          if (cursor != null) {
            cursor.close();
          }
        }
      }
    };
  }

  @NonNull
  public LiveData<MoviesApiResponse<MovieEntity>> query() {

    return new LiveData<MoviesApiResponse<MovieEntity>>() {

      @Override
      protected void onActive() {
        super.onActive();
        final Cursor cursor = app
                .getContentResolver()
                .query(FavoritesMovieContract.FavoritesMovies.CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                );
        try {
          if (cursor != null) {
            if (cursor.getCount() > 0) {
              cursor.moveToFirst();
              MoviesApiResponse data = new MoviesApiResponse<MovieDetailEntity>();
              data.setResults(PopMovisUtils.toMoviesResultList(cursor));
              postValue(data);
            } else {
              postValue(null);
            }
          }
        } finally {
          if (cursor != null) {
            cursor.close();
          }
        }
      }
    };
  }


  @NonNull
  public LiveData<Boolean> performFavorites(@NonNull final MovieDetailEntity movie) {
    return Transformations.switchMap(this.query(movie.getId()), isFavorite -> isFavorite ? this.removeFromFavorites(movie) : this.addToFavorites(movie));
  }

  @NonNull
  private LiveData<Boolean> addToFavorites(@NonNull final MovieDetailEntity movie) {

    return new MutableLiveData<Boolean>() {
      @Override
      protected void onActive() {
        super.onActive();
        final Uri uri = app.getContentResolver().insert(
                FavoritesMovieContract.FavoritesMovies.CONTENT_URI,
                PopMovisUtils.toContentValues(movie)
        );

        postValue(uri != null && uri.getLastPathSegment().equals(String.valueOf(movie.getId())));
      }
    };
  }

  @NonNull
  public LiveData<Boolean> removeFromFavorites(@NonNull final MovieDetailEntity movie) {
    return new LiveData<Boolean>() {
      @Override
      protected void onActive() {
        super.onActive();
        final int count = app.getContentResolver().delete(
                PopMovisUtils.itemUri(movie.getId()),
                FavoritesMovieContract.FavoritesMovies.COLUMN_MOVIE_ID + "=?",
                new String[]{String.valueOf(movie.getId())}
        );
        postValue(count == 1);
      }
    };
  }
}
