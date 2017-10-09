package com.malouane.popularmovies.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.malouane.popularmovies.data.database.FavoritesMovieContract;
import com.malouane.popularmovies.data.entity.MovieDetailEntity;
import com.malouane.popularmovies.data.entity.MovieEntity;

import java.util.ArrayList;
import java.util.List;

public class PopMovisUtils {

    public static ContentValues toContentValues(@NonNull final MovieDetailEntity movie) {
        final ContentValues values = new ContentValues();
        values.put(FavoritesMovieContract.FavoritesMovies.COLUMN_MOVIE_ID, movie.getId());
        values.put(FavoritesMovieContract.FavoritesMovies.COLUMN_TITLE, movie.getTitle());
        values.put(FavoritesMovieContract.FavoritesMovies.COLUMN_RELEASE_DATE, movie.getReleaseDate());
        values.put(FavoritesMovieContract.FavoritesMovies.COLUMN_OVERVIEW, movie.getOverview());
        values.put(FavoritesMovieContract.FavoritesMovies.COLUMN_POSTER_PATH, movie.getPosterPath());
        values.put(FavoritesMovieContract.FavoritesMovies.COLUMN_BACKDROP_PATH, movie.getBackdropPath());
        values.put(FavoritesMovieContract.FavoritesMovies.COLUMN_POPULARITY, movie.getPopularity());
        values.put(FavoritesMovieContract.FavoritesMovies.COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
        return values;
    }

    /**
     * Convert {@link Cursor} to {@link @{@link MovieDetailEntity} }
     */
    public static List<MovieEntity> toMoviesResultList(@NonNull final Cursor cursor) {
        List<MovieEntity> movies = new ArrayList<>();
        final int idIndex = cursor.getColumnIndex(FavoritesMovieContract.FavoritesMovies.COLUMN_MOVIE_ID);
        final int titleIndex = cursor.getColumnIndex(FavoritesMovieContract.FavoritesMovies.COLUMN_TITLE);
        final int posterIndex = cursor.getColumnIndex(FavoritesMovieContract.FavoritesMovies.COLUMN_POSTER_PATH);
        final int backdropIndex = cursor.getColumnIndex(FavoritesMovieContract.FavoritesMovies.COLUMN_BACKDROP_PATH);
        final int overviewIndex = cursor.getColumnIndex(FavoritesMovieContract.FavoritesMovies.COLUMN_OVERVIEW);
        final int releaseIndex = cursor.getColumnIndex(FavoritesMovieContract.FavoritesMovies.COLUMN_RELEASE_DATE);
        final int voteAverageIndex = cursor.getColumnIndex(FavoritesMovieContract.FavoritesMovies.COLUMN_VOTE_AVERAGE);

        try {
            while (cursor.moveToNext()) {
                movies.add(new MovieEntity(
                        cursor.getInt(idIndex),
                        cursor.getString(posterIndex),
                        false,
                        cursor.getString(overviewIndex),
                        cursor.getString(titleIndex),
                        0,
                        cursor.getDouble(voteAverageIndex),
                        cursor.getString(backdropIndex),
                        cursor.getString(releaseIndex)
                ));
            }

        } finally {
            cursor.close();
        }
        return movies;
    }


    public static Uri itemUri(@NonNull final Integer id) {
        assert id != null;
        return FavoritesMovieContract.FavoritesMovies.CONTENT_URI.buildUpon().appendPath(id.toString()).build();
    }

    public static String buildYouTubeLink(@NonNull final String key) {
        final Uri.Builder builder = Uri.parse("https://www.youtube.com/watch").buildUpon();
        builder.appendQueryParameter("v", key);
        return builder.build().toString();
    }

    public static void shareTrailer(Context ctx, String ytKey) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        share.putExtra(Intent.EXTRA_SUBJECT, "Check out this Trailer, You May Like it !");
        share.putExtra(Intent.EXTRA_TEXT, buildYouTubeLink(ytKey));
        ctx.startActivity(Intent.createChooser(share, "Share Trailer"));
    }

    public static void openTrailer(final Context context, @NonNull final String link) {
        Intent share = new Intent(Intent.ACTION_VIEW);
        share.setData(Uri.parse(link));
        context.startActivity(share);
    }
}
