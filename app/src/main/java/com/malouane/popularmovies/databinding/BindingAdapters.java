package com.malouane.popularmovies.databinding;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.malouane.popularmovies.R;
import com.malouane.popularmovies.data.network.ApiConstants;
import com.squareup.picasso.Picasso;

public final class BindingAdapters {

  @BindingAdapter(value = "url") public static void loadImageUrl(ImageView view, String url) {
    if (url != null && !url.equals("")) {
      Picasso.with(view.getContext())
              .load(String.format(ApiConstants.IMAGE_ENDPOINT_PREFIX, url))
          .placeholder(R.drawable.ic_movie_black_24dp)
          .into(view);
    }
  }

  @BindingAdapter(value = "showView")
  public static void showView(View v, boolean value) {
    if (v != null) v.setVisibility(value ? View.VISIBLE : View.GONE);
  }

  @BindingAdapter(value = "hideView")
  public static void hideView(View v, boolean value) {
    showView(v, !value);
  }
}
