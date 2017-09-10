package com.malouane.popularmovies.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.malouane.popularmovies.data.DataManager;
import com.malouane.popularmovies.data.network.MoviesApiResponse;
import com.malouane.popularmovies.vm.BaseViewModel;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class MovieListViewModel extends BaseViewModel {

  private MutableLiveData<MoviesApiResponse> moviesList;
  private DataManager dataManager;


  @Inject public MovieListViewModel(DataManager dataManager) {
    this.dataManager = dataManager;
  }

  public LiveData<MoviesApiResponse> getMoviesList(String listType) {
    assert moviesList.getValue() != null;
    if (moviesList == null || moviesList.getValue().getResults().isEmpty()) {
      moviesList = new MutableLiveData<>();
      loadMoviesList(moviesList, listType);
    }
    return moviesList;
  }

  private void loadMoviesList(MutableLiveData<MoviesApiResponse> moviesList, String listType) {
    addDisposable(performGeMovies(listType).subscribe(
        (moviesApiResponse, throwable) -> moviesList.setValue(moviesApiResponse)));
  }

  private Single<MoviesApiResponse> performGeMovies(String listType) {
    return dataManager.performGetMovies(listType)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
