package com.malouane.popularmovies.ui.detail;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.malouane.popularmovies.R;
import com.malouane.popularmovies.databinding.ActivityMovieDetailBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MovieDetailActivity extends AppCompatActivity implements LifecycleRegistryOwner {

  private static final String KEY_MOVIE_ID = "KEY_MOVIE_ID";
  private static final String KEY_MOVIE_POSTER = "KEY_MOVIE_POSTER";

  LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
  ActivityMovieDetailBinding binding;

  @Inject MovieDetailViewModel movieDetailViewModel;

  MovieReviewsAdapter movieReviewsAdapter;

  public static Intent newIntent(Context context, int movieID, String posterPath) {
    Intent intent = new Intent(context, MovieDetailActivity.class);
    intent.putExtra(KEY_MOVIE_ID, movieID);
    intent.putExtra(KEY_MOVIE_POSTER, posterPath);
    return intent;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

    setSupportActionBar(binding.toolbar);
    assert getSupportActionBar() != null;
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {

      if (bundle.containsKey(KEY_MOVIE_POSTER) && bundle.containsKey(KEY_MOVIE_ID)) {

        movieDetailViewModel.isFavorite().observe(this, isFavorite -> {
          if (isFavorite == null) isFavorite = false;
          binding.favoriteFab.setImageResource(isFavorite ? R.drawable.ic_favorite_border_black_24dp : R.drawable.ic_favorite_black_24dp);
          initFAB();
        });

        binding.setPosterPath(getIntent().getStringExtra(KEY_MOVIE_POSTER));
        movieDetailViewModel.setMovieId(getIntent().getIntExtra(KEY_MOVIE_ID, 0));
        movieDetailViewModel.getMovieDetails()
                .observe(this, movie -> binding.setMovie(movie));
      }
    }
  }

  private void initFAB() {
    binding.favoriteFab.setOnClickListener(view1 ->
            movieDetailViewModel.performFavorites(binding.getMovie()).observe(this, aBoolean -> movieDetailViewModel.retry()));
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        ActivityCompat.finishAfterTransition(this);
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public LifecycleRegistry getLifecycle() {
    return lifecycleRegistry;
  }
}
