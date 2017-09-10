package com.malouane.popularmovies.di;

import android.app.Application;
import com.malouane.popularmovies.PopMovisApp;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import javax.inject.Singleton;

@Singleton @Component(modules = {
    AppModule.class, AndroidInjectionModule.class, ActivityBuilderModule.class
}) public interface AppComponent {

  void inject(PopMovisApp app);

  @Component.Builder interface Builder {
    @BindsInstance Builder application(Application application);

    AppComponent build();
  }
}
