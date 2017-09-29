package com.malouane.popularmovies.databinding;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.malouane.popularmovies.data.entity.MovieDetailEntity;
import com.malouane.popularmovies.data.entity.MovieGenre;

class SimpleListBindingAdapter {

  @BindingAdapter({ "bind:genres" })
  public static void setGenres(LinearLayout parent, MovieDetailEntity movieDetailEntity) {
    if (movieDetailEntity == null) return;

    for (int i = 0, size = movieDetailEntity.getMovieGenres().size(); i < size; i++) {
      TextView tv = new TextView(parent.getContext());
      tv.setBackgroundColor(Color.LTGRAY);
      tv.setPadding(10, 10, 10, 10);
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
      params.setMargins(4, 0, 8, 0);
      tv.setLayoutParams(params);
      tv.setTextColor(Color.WHITE);
      MovieGenre genre = movieDetailEntity.getMovieGenres().get(i);
      tv.setText(genre.getName());
      parent.addView(tv);
    }
  }
}
