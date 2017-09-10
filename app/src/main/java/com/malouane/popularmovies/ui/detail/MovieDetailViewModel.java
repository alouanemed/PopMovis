package com.malouane.popularmovies.ui.detail;

import android.arch.lifecycle.ViewModel;
import com.malouane.popularmovies.data.DataManager;
import javax.inject.Inject;

public class MovieDetailViewModel extends ViewModel {

  DataManager dataManager;

  @Inject public MovieDetailViewModel(DataManager dataManager) {
    this.dataManager = dataManager;
  }
}
