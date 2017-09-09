package com.malouane.popularmovies.ui.main;

import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.malouane.popularmovies.data.MovieEntity;
import com.malouane.popularmovies.databinding.FragmentMovieListBinding;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

public class MovieListFragment extends LifecycleFragment implements MovieListCallback {

  @Inject MovieListViewModel movieListViewModel;

  FragmentMovieListBinding binding;
  MovieListAdapter adapter;

  public static MovieListFragment newInstance() {
    Bundle args = new Bundle();
    MovieListFragment fragment = new MovieListFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidSupportInjection.inject(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentMovieListBinding.inflate(inflater, container, false);
    binding.recyclerViewMoviesList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    adapter = new MovieListAdapter(this);
    binding.recyclerViewMoviesList.setAdapter(adapter);
    return binding.getRoot();
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    movieListViewModel.getMoviesList()
        .observe(this, listResource -> binding.setMoviesList(listResource));
  }

  @Override public void onMovieClicked(MovieEntity movieEntity, View sharedView) {

  }
}
