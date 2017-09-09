package com.malouane.popularmovies.data.network;

import com.google.gson.annotations.SerializedName;
import com.malouane.popularmovies.data.MovieEntity;
import java.util.List;

public class MoviesApiResponse {
  @SerializedName("results") private List<MovieEntity> results;

  public List<MovieEntity> getResults() {
    return results;
  }

  public void setResults(List<MovieEntity> results) {
    this.results = results;
  }
}
