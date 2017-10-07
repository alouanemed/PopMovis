package com.malouane.popularmovies.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.malouane.popularmovies.data.entity.MovieEntity;
import com.malouane.popularmovies.databinding.ItemMovieBinding;
import com.malouane.popularmovies.ui.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends BaseAdapter<MovieListAdapter.MovieViewHolder, MovieEntity> {

  private final MovieListCallback movieListCallback;
  private List<MovieEntity> movieEntities;

  public MovieListAdapter(@NonNull MovieListCallback movieListCallback) {
    movieEntities = new ArrayList<>();
    this.movieListCallback = movieListCallback;
  }

  @Override public void setData(List<MovieEntity> movieEntities) {
    this.movieEntities = movieEntities;
    notifyDataSetChanged();
  }

  public void clearData() {
    movieEntities.clear();
  }

  @Override public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    return MovieViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup,
        movieListCallback);
  }

  @Override public void onBindViewHolder(MovieViewHolder viewHolder, int i) {
    viewHolder.onBind(movieEntities.get(i));
  }

  @Override public int getItemCount() {
    return movieEntities.size();
  }

  static class MovieViewHolder extends RecyclerView.ViewHolder {

    ItemMovieBinding binding;

    public MovieViewHolder(ItemMovieBinding binding, MovieListCallback callback) {
      super(binding.getRoot());
      this.binding = binding;
      binding.getRoot()
          .setOnClickListener(
                  v -> callback.onMovieClicked(binding.getMovie(), binding.ivCover));
    }

    public static MovieViewHolder create(LayoutInflater inflater, ViewGroup parent,
        MovieListCallback callback) {
      ItemMovieBinding inflate = ItemMovieBinding.inflate(inflater, parent, false);
      return new MovieViewHolder(inflate, callback);
    }

    public void onBind(MovieEntity movieEntity) {
      binding.setMovie(movieEntity);
      binding.executePendingBindings();
    }
  }
}
