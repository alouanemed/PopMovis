package com.malouane.popularmovies.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import com.malouane.popularmovies.data.network.MoviesApiResponse;
import com.malouane.popularmovies.ui.BaseAdapter;

public final class ListBindingAdapter {
  @BindingAdapter({ "bind:data" })
  public static void setItems(RecyclerView recyclerView, MoviesApiResponse moviesApiResponse) {
    RecyclerView.Adapter adapter = recyclerView.getAdapter();
    if (adapter == null) return;

    if (moviesApiResponse == null || moviesApiResponse.getResults() == null) return;

    if (adapter instanceof BaseAdapter) {
      ((BaseAdapter) adapter).setData(moviesApiResponse.getResults());
    }
  }
}
