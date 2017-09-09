package com.malouane.popularmovies.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.malouane.popularmovies.R;
import com.malouane.popularmovies.databinding.ActivityMainBinding;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

  @Inject DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

  ActivityMainBinding binding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
  }

  @Override public AndroidInjector<Fragment> supportFragmentInjector() {
    return fragmentAndroidInjector;
  }
}