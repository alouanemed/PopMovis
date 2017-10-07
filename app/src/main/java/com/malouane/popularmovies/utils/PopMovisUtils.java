package com.malouane.popularmovies.utils;

import android.content.ContentValues;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.malouane.popularmovies.data.database.FavoritesMovieContract;
import com.malouane.popularmovies.data.entity.MovieDetailEntity;

/**
 * Created by alouanemed on 10/5/2017.
 */

public class PopMovisUtils {

    public static ContentValues toContentValues(@NonNull final MovieDetailEntity movie) {
        final ContentValues values = new ContentValues();
        values.put(FavoritesMovieContract.FavoritesMovies.COLUMN_MOVIE_ID, movie.getId());
        values.put(FavoritesMovieContract.FavoritesMovies.COLUMN_TITLE, movie.getTitle());
        values.put(FavoritesMovieContract.FavoritesMovies.COLUMN_RELEASE_DATE, movie.getReleaseDate());
        values.put(FavoritesMovieContract.FavoritesMovies.COLUMN_OVERVIEW, movie.getOverview());
        values.put(FavoritesMovieContract.FavoritesMovies.COLUMN_POSTER_PATH, movie.getPosterPath());
        values.put(FavoritesMovieContract.FavoritesMovies.COLUMN_BACKDROP_PATH, movie.getBackdropPath());
        //values.put(FavoritesMovieContract.FavoritesMovies.COLUMN_POPULARITY, movie.getPopularity());
        values.put(FavoritesMovieContract.FavoritesMovies.COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
        return values;
    }

    public static Uri itemUri(@NonNull final Integer id) {
        assert id != null;
        return FavoritesMovieContract.FavoritesMovies.CONTENT_URI.buildUpon().appendPath(id.toString()).build();
    }
}
