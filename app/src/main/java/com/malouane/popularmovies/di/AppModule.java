package com.malouane.popularmovies.di;

import com.malouane.popularmovies.BuildConfig;
import com.malouane.popularmovies.data.DataManager;
import com.malouane.popularmovies.data.MovieDBService;
import com.malouane.popularmovies.data.network.ApiConstants;
import com.malouane.popularmovies.data.network.IMoviesHttp;
import com.malouane.popularmovies.data.network.MoviesHttp;
import com.malouane.popularmovies.data.network.RequestInterceptor;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class) public class AppModule {

  @Provides @Singleton OkHttpClient provideOkHttpClient() {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    logging.setLevel(
        BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BASIC);

    return new OkHttpClient.Builder().addInterceptor(chain -> {
      Request request = chain.request();
      Request newReq = request.newBuilder().build();
      return chain.proceed(newReq);
    }).addInterceptor(logging).addInterceptor(new RequestInterceptor()).build();
  }

  @Provides @Singleton MovieDBService provideRetrofit(OkHttpClient okHttpClient) {
    OkHttpClient.Builder httpClientBuilder = okHttpClient.newBuilder();

    return new Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .callFactory(httpClientBuilder.build())
        .client(okHttpClient)
        .build()
        .create(MovieDBService.class);
  }

  @Provides @Singleton DataManager provideDataManager(IMoviesHttp http) {
    return new DataManager(http);
  }

  @Provides @Singleton IMoviesHttp provideHttpHelper(MoviesHttp http) {
    return http;
  }
}