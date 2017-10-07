package com.malouane.popularmovies.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.malouane.popularmovies.data.DataManager;
import com.malouane.popularmovies.data.entity.MovieDetailEntity;
import com.malouane.popularmovies.data.entity.MovieReviewEntity;
import com.malouane.popularmovies.data.entity.MovieTrailerEntity;
import com.malouane.popularmovies.data.network.MoviesApiResponse;
import com.malouane.popularmovies.vm.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MovieDetailViewModel extends BaseViewModel {

  private final MutableLiveData<Integer> movieId = new MutableLiveData<>();
  private final LiveData<Boolean> isFavorite;
  private DataManager dataManager;
  private MutableLiveData<MovieDetailEntity> movie;
  private MutableLiveData<MoviesApiResponse<MovieReviewEntity>> movieReviews;
  private MutableLiveData<MoviesApiResponse<MovieTrailerEntity>> movieTrailers;

  @Inject public MovieDetailViewModel(DataManager dataManager) {
    this.dataManager = dataManager;
    isFavorite = Transformations.switchMap(movieId, dataManager::query);
  }

  public LiveData<MovieDetailEntity> getMovieDetails() {
    if (movie == null) {
      movie = new MutableLiveData<>();
      movieReviews = new MutableLiveData<>();
      movieTrailers = new MutableLiveData<>();
      loadMovieDetails(movie, movieReviews, movieTrailers, movieId.getValue());
    }
    return movie;
  }

  void setMovieId(@NonNull final Integer id) {
    if (id.equals(this.movieId.getValue())) {
      return;
    }
    this.movieId.setValue(id);
  }

  public MutableLiveData<MoviesApiResponse<MovieReviewEntity>> getMovieReviews() {
    return movieReviews;
  }

  public MutableLiveData<MoviesApiResponse<MovieTrailerEntity>> getMovieTrailers() {
    return movieTrailers;
  }

  private void loadMovieDetails(MutableLiveData<MovieDetailEntity> movie, MutableLiveData<MoviesApiResponse<MovieReviewEntity>> movieReviews, MutableLiveData<MoviesApiResponse<MovieTrailerEntity>> movieTrailers, @NonNull int movieId) {
    addDisposable(performGeMovie(movieId).subscribe((movieDetailEntity, throwable) -> {
      if (movieDetailEntity != null) movie.setValue(movieDetailEntity);
    }));

    addDisposable(performGeMovieReviews(movieId).subscribe((review, throwable) -> {
      if (review != null) movieReviews.setValue(review);
    }));

    addDisposable(performGeMovieTrailers(movieId).subscribe((trailer, throwable) -> {
      if (trailer != null) movieTrailers.setValue(trailer);
    }));
  }

  private Single<MovieDetailEntity> performGeMovie(int movieId) {
    return dataManager.performGetMovie(movieId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread());
  }

  private Single<MoviesApiResponse<MovieReviewEntity>> performGeMovieReviews(int movieId) {
    return dataManager.performGetMovieReviews(movieId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread());
  }

  private Single<MoviesApiResponse<MovieTrailerEntity>> performGeMovieTrailers(int movieId) {
    return dataManager.performGetMovieTrailers(movieId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread());
  }

  public LiveData<Boolean> performFavorites(MovieDetailEntity movie) {
      Timber.d("Fav id " + movie.getId());
    return dataManager.performFavorites(movie);
  }

  @NonNull
  LiveData<Boolean> isFavorite() {
    return isFavorite;
  }

  void retry() {
    if (this.movieId.getValue() != null)
      this.movieId.setValue(this.movieId.getValue());
  }
}
