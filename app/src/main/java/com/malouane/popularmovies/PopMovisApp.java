package com.malouane.popularmovies;

import android.app.Activity;
import android.app.Application;
import com.malouane.popularmovies.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Initialize dependency graph for our app using Dagger
 */

public class PopMovisApp extends Application implements HasActivityInjector {

  @Inject DispatchingAndroidInjector<Activity> activityDispatchingInjector;

  @Override public void onCreate() {
    super.onCreate();
    initializeComponent();
  }

  private void initializeComponent() {
    DaggerAppComponent.builder().application(this).build().inject(this);

    //init Timber
    Timber.plant(new Timber.DebugTree());
  }

  @Override public AndroidInjector<Activity> activityInjector() {
    return activityDispatchingInjector;
  }
}
