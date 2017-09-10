package com.malouane.popularmovies.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.malouane.popularmovies.data.DataManager;
import com.malouane.popularmovies.data.MovieDetailEntity;
import com.malouane.popularmovies.vm.BaseViewModel;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class MovieDetailViewModel extends BaseViewModel {

  DataManager dataManager;
  MutableLiveData<MovieDetailEntity> movie;

  @Inject public MovieDetailViewModel(DataManager dataManager) {
    this.dataManager = dataManager;
  }

  public LiveData<MovieDetailEntity> getMovieWithId(int movieId) {
    if (movie == null) {
      movie = new MutableLiveData<MovieDetailEntity>();
      loadMoviesList(movie, movieId);
    }
    return movie;
  }

  private void loadMoviesList(MutableLiveData<MovieDetailEntity> movie, int movieId) {
    addDisposable(performGeMovie(movieId).subscribe((movieDetailEntity, throwable) -> {
      if (movieDetailEntity != null) movie.setValue(movieDetailEntity);
    }));
  }

  public Single<MovieDetailEntity> performGeMovie(int movieId) {
    return dataManager.performGetMovie(movieId)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
