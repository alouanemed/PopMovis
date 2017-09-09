package com.malouane.popularmovies.di;

import com.malouane.popularmovies.ui.main.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module public abstract class ActivityBuilderModule {

  @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
  abstract MainActivity mainActivity();
}
