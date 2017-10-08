package com.malouane.popularmovies.ui.detail;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.malouane.popularmovies.data.entity.MovieTrailerEntity;
import com.malouane.popularmovies.databinding.ItemMovieTrailerBinding;
import com.malouane.popularmovies.ui.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class MovieTrailersAdapter extends BaseAdapter<MovieTrailersAdapter.MovieTrailerViewHolder, MovieTrailerEntity> {

    List<MovieTrailerEntity> trailersList;
    TrailerListCallback callback;

    public MovieTrailersAdapter(TrailerListCallback callback) {
        this.callback = callback;
        trailersList = new ArrayList<>();
    }

    @Override
    public void setData(List<MovieTrailerEntity> reviews) {
        this.trailersList = reviews;
        notifyDataSetChanged();
    }

    public void clearData() {
        trailersList.clear();
    }

    @Override
    public MovieTrailersAdapter.MovieTrailerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return MovieTrailersAdapter.MovieTrailerViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, callback);
    }

    @Override
    public void onBindViewHolder(MovieTrailersAdapter.MovieTrailerViewHolder viewHolder, int i) {
        viewHolder.onBind(trailersList.get(i));
    }

    @Override
    public int getItemCount() {
        return trailersList.size();
    }

    static class MovieTrailerViewHolder extends RecyclerView.ViewHolder {

        ItemMovieTrailerBinding binding;

        public MovieTrailerViewHolder(ItemMovieTrailerBinding binding, TrailerListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot()
                    .setOnClickListener(
                            v -> callback.onTrailerClicked(binding.getTrailer()));
            binding.ivShare
                    .setOnClickListener(
                            v -> callback.onTrailerShareClicked(binding.getTrailer().getKey()));

        }

        public static MovieTrailersAdapter.MovieTrailerViewHolder create(LayoutInflater inflater, ViewGroup parent, TrailerListCallback callback) {
            ItemMovieTrailerBinding inflate = ItemMovieTrailerBinding.inflate(inflater, parent, false);
            return new MovieTrailersAdapter.MovieTrailerViewHolder(inflate, callback);
        }

        public void onBind(MovieTrailerEntity entity) {
            binding.setTrailer(entity);
            binding.executePendingBindings();
        }
    }
}