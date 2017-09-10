package com.malouane.popularmovies.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.malouane.popularmovies.ui.detail.MovieDetailViewModel;
import com.malouane.popularmovies.ui.main.MovieListViewModel;
import com.malouane.popularmovies.vm.PopMovisViewModelFactory;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module public abstract class ViewModelModule {

  @Binds @IntoMap @ViewModelKey(MovieListViewModel.class)
  abstract ViewModel bindsMovieListViewModel(MovieListViewModel movieListViewModel);

  @Binds @IntoMap @ViewModelKey(MovieDetailViewModel.class)
  abstract ViewModel bindsMovieDetailViewModel(MovieDetailViewModel movieDetailViewModel);

  @Binds abstract ViewModelProvider.Factory bindsViewModelFactory(
      PopMovisViewModelFactory movisViewModelFactory);
}