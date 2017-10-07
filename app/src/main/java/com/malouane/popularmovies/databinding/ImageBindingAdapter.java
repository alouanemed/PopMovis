package com.malouane.popularmovies.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.malouane.popularmovies.R;
import com.malouane.popularmovies.data.network.ApiConstants;
import com.squareup.picasso.Picasso;

public final class ImageBindingAdapter {

  @BindingAdapter(value = "url") public static void loadImageUrl(ImageView view, String url) {
    if (url != null && !url.equals("")) {
      Picasso.with(view.getContext())
              .load(String.format(ApiConstants.IMAGE_ENDPOINT_PREFIX, url))
          .placeholder(R.drawable.ic_movie_black_24dp)
          .into(view);
    }
  }
}
