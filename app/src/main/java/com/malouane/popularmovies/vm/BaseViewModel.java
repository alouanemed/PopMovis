package com.malouane.popularmovies.vm;

import android.arch.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {
  CompositeDisposable compositeDisposable;

  protected void initializeViewModel() {
    compositeDisposable = new CompositeDisposable();
  }

  @Override protected void onCleared() {
    super.onCleared();
    if (compositeDisposable != null) compositeDisposable.clear();
  }

  public void addDisposable(Disposable disposable) {
    if (compositeDisposable == null) compositeDisposable = new CompositeDisposable();
    compositeDisposable.add(disposable);
  }
}
