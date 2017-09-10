package com.malouane.popularmovies.databinding;

import android.databinding.BindingAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.malouane.popularmovies.data.MovieDetailEntity;
import com.malouane.popularmovies.data.MovieGenre;

class SimpleListBindingAdapter {

  @BindingAdapter({ "bind:genres" })
  public static void setGenres(LinearLayout parent, MovieDetailEntity movieDetailEntity) {
    if (movieDetailEntity == null) return;

    for (int i = 0, size = movieDetailEntity.getMovieGenres().size(); i < size; i++) {
      TextView tv = new TextView(parent.getContext());
      MovieGenre genre = movieDetailEntity.getMovieGenres().get(i);
      tv.setText(genre.getName());
      parent.addView(tv);
    }
  }
}
