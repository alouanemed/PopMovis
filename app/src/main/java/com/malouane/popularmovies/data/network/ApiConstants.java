package com.malouane.popularmovies.data.network;

import com.malouane.popularmovies.BuildConfig;

/**
 * Api Constant will be used when configuring API service
 */

public class ApiConstants {
  public static final String BASE_URL = BuildConfig.API_URL;
  public static final String API_KEY = BuildConfig.API_KEY;

  public static final String IMAGE_ENDPOINT_PREFIX = "https://image.tmdb.org/t/p/w185/";
}
