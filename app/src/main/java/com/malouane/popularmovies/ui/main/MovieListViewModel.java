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
import timber.log.Timber;

public class MovieListViewModel extends BaseViewModel {

  MutableLiveData<MoviesApiResponse> moviesList;
  DataManager dataManager;

  @Inject public MovieListViewModel(DataManager dataManager) {
    this.dataManager = dataManager;
  }

  public LiveData<MoviesApiResponse> getMoviesList() {
    if (moviesList == null) {
      moviesList = new MutableLiveData<MoviesApiResponse>();
      loadMoviesList(moviesList);
    }
    return moviesList;
  }

  private void loadMoviesList(MutableLiveData<MoviesApiResponse> moviesList) {
    addDisposable(performGeMovies().subscribe(
        (moviesApiResponse, throwable) -> moviesList.setValue(moviesApiResponse)));
  }

  public Single<MoviesApiResponse> performGeMovies() {
    Timber.d("performGeMovies");
    return dataManager.performGetMovies("popular")
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
