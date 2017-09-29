package com.malouane.popularmovies.ui.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.malouane.popularmovies.data.entity.MovieReviewEntity;
import com.malouane.popularmovies.databinding.ItemMovieReviewBinding;
import com.malouane.popularmovies.ui.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class MovieReviewsAdapter extends BaseAdapter<MovieReviewsAdapter.MovieReviewViewHolder, MovieReviewEntity> {

    List<MovieReviewEntity> reviewsList;

    public MovieReviewsAdapter() {
        reviewsList = new ArrayList<>();
    }

    @Override
    public void setData(List<MovieReviewEntity> reviews) {
        this.reviewsList = reviews;
        notifyDataSetChanged();
    }

    public void clearData() {
        reviewsList.clear();
    }

    @Override
    public MovieReviewViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return MovieReviewViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup);
    }

    @Override
    public void onBindViewHolder(MovieReviewViewHolder viewHolder, int i) {
        viewHolder.onBind(reviewsList.get(i));
    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    static class MovieReviewViewHolder extends RecyclerView.ViewHolder {

        ItemMovieReviewBinding binding;


        public MovieReviewViewHolder(ItemMovieReviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public static MovieReviewViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            ItemMovieReviewBinding inflate = ItemMovieReviewBinding.inflate(inflater, parent, false);
            return new MovieReviewViewHolder(inflate);
        }

        public void onBind(MovieReviewEntity entity) {
            binding.setReview(entity);
            binding.executePendingBindings();
        }
    }
}
