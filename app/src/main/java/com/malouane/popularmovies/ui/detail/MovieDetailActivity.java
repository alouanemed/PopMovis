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
import com.malouane.popularmovies.data.MovieEntity;
import com.malouane.popularmovies.databinding.ActivityMovieDetailBinding;
import dagger.android.AndroidInjection;
import javax.inject.Inject;

public class MovieDetailActivity extends AppCompatActivity implements LifecycleRegistryOwner {

  private static final String KEY_MOVIE_ID = "KEY_MOVIE_ID";

  LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
  ActivityMovieDetailBinding binding;

  @Inject MovieDetailViewModel movieDetailViewModel;

  public static Intent newIntent(Context context, MovieEntity movie) {
    Intent intent = new Intent(context, MovieDetailActivity.class);
    intent.putExtra(KEY_MOVIE_ID, movie);
    return intent;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    assert getIntent().getExtras() != null;
    MovieEntity currentMovie = getIntent().getExtras().getParcelable(KEY_MOVIE_ID);
    binding.setMovie(currentMovie);
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
