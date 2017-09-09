package com.malouane.popularmovies.di;

import com.malouane.popularmovies.ui.main.MovieListFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module public abstract class FragmentBuildersModule {
  @ContributesAndroidInjector abstract MovieListFragment listFragment();
}