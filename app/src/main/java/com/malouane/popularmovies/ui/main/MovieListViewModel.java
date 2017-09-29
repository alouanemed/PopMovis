package com.malouane.popularmovies.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.malouane.popularmovies.data.DataManager;
import com.malouane.popularmovies.data.entity.MovieEntity;
import com.malouane.popularmovies.data.network.MoviesApiResponse;
import com.malouane.popularmovies.vm.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieListViewModel extends BaseViewModel {

    private MutableLiveData<MoviesApiResponse<MovieEntity>> moviesList;
  private DataManager dataManager;


  @Inject public MovieListViewModel(DataManager dataManager) {
    this.dataManager = dataManager;
  }

    public LiveData<MoviesApiResponse<MovieEntity>> getMoviesList(String listType) {
    boolean shouldGetData = false;

    if (moviesList == null) {
      shouldGetData = true;
    } else {
      if (moviesList.getValue() == null)
        shouldGetData = true;
    }

    if (shouldGetData) {
      moviesList = new MutableLiveData<>();
      loadMoviesList(moviesList, listType);
    }

    return moviesList;
  }

    private void loadMoviesList(MutableLiveData<MoviesApiResponse<MovieEntity>> moviesList, String listType) {
    addDisposable(performGeMovies(listType).subscribe(
        (moviesApiResponse, throwable) -> moviesList.setValue(moviesApiResponse)));
  }

    private Single<MoviesApiResponse<MovieEntity>> performGeMovies(String listType) {
    return dataManager.performGetMovies(listType)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
