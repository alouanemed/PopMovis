package com.malouane.popularmovies.ui.main;

import android.view.View;
import com.malouane.popularmovies.data.MovieEntity;

interface MovieListCallback {
  void onMovieClicked(MovieEntity movieEntity, View sharedView);
}
