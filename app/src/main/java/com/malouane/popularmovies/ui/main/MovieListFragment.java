package com.malouane.popularmovies.ui.main;

import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.malouane.popularmovies.R;
import com.malouane.popularmovies.data.MovieEntity;
import com.malouane.popularmovies.databinding.FragmentMovieListBinding;
import com.malouane.popularmovies.ui.detail.MovieDetailActivity;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

public class MovieListFragment extends LifecycleFragment implements MovieListCallback {

  @Inject MovieListViewModel movieListViewModel;

  private FragmentMovieListBinding binding;
  private MovieListAdapter adapter;

  public static MovieListFragment newInstance() {
    Bundle args = new Bundle();
    MovieListFragment fragment = new MovieListFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidSupportInjection.inject(this);
    setHasOptionsMenu(true);
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
    getMoviesSortedBy("popular");
  }

  private void getMoviesSortedBy(String sort) {
    movieListViewModel.getMoviesList(sort)
        .observe(this, listResource -> binding.setMoviesList(listResource));
  }

  private void clearList() {
    adapter.clearData();
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_main2, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_sort_top:
        clearList();
        getMoviesSortedBy("top_rated");
        return true;
      case R.id.action_sort_popular:
        clearList();
        getMoviesSortedBy("popular");
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override public void onMovieClicked(MovieEntity movieEntity, View sharedView) {
    ActivityOptionsCompat options = ActivityOptionsCompat.
        makeSceneTransitionAnimation(getActivity(), sharedView, getString(R.string.shared_image));
    startActivity(MovieDetailActivity.newIntent(getActivity(), movieEntity.getId(),
        movieEntity.getPosterPath()),
        options.toBundle());
  }
}