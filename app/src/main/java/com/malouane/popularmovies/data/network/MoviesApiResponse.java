package com.malouane.popularmovies.data.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesApiResponse<T> {
    @SerializedName("results")
    private List<T> results;

    public List<T> getResults() {
    return results;
  }

    public void setResults(List<T> results) {
    this.results = results;
  }
}
