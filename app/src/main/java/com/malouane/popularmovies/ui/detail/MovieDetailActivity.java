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
import dagger.android.AndroidInjection;
import javax.inject.Inject;

public class MovieDetailActivity extends AppCompatActivity implements LifecycleRegistryOwner {

  private static final String KEY_MOVIE_ID = "KEY_MOVIE_ID";
  private static final String KEY_MOVIE_POSTER = "KEY_MOVIE_POSTER";

  LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
  ActivityMovieDetailBinding binding;

  @Inject MovieDetailViewModel movieDetailViewModel;

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

    binding.setPosterPath(getIntent().getStringExtra(KEY_MOVIE_POSTER));
    movieDetailViewModel.getMovieWithId(getIntent().getIntExtra(KEY_MOVIE_ID, 0))
        .observe(this, movie -> binding.setMovie(movie));
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
