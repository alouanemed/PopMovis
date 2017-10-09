package com.malouane.popularmovies.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MovieEntity implements Parcelable {

  @SuppressWarnings("unused") public static final Parcelable.Creator<MovieEntity> CREATOR =
      new Parcelable.Creator<MovieEntity>() {
        @Override public MovieEntity createFromParcel(Parcel in) {
          return new MovieEntity(in);
        }

        @Override public MovieEntity[] newArray(int size) {
          return new MovieEntity[size];
        }
      };
  @SerializedName("id") private int id;
  @SerializedName("poster_path") private String posterPath;
  @SerializedName("adult") private boolean adult;
  @SerializedName("overview") private String overview;
  @SerializedName("original_title") private String originalTitle;
  @SerializedName("title") private String title;
  @SerializedName("vote_count") private int voteCount;
  @SerializedName("vote_average") private double voteAverage;
  @SerializedName("backdrop_path") private String backdropPath;
  @SerializedName("original_language") private String originalLanguage;
  @SerializedName("release_date") private String releaseDate;


  public MovieEntity(Parcel in) {
    id = in.readInt();
    posterPath = in.readString();
    adult = in.readByte() != 0x00;
    overview = in.readString();
    originalTitle = in.readString();
    title = in.readString();
    voteCount = in.readInt();
    voteAverage = in.readDouble();
    backdropPath = in.readString();
    originalLanguage = in.readString();
    releaseDate = in.readString();
  }

  public MovieEntity(int id, String poster, boolean b, String overview, String title, int i, double voteAverage, String backdropPath, String releaseDate) {
    this.id = id;
    this.posterPath = poster;
    this.adult = b;
    this.overview = overview;
    this.title = title;
    this.voteAverage = voteAverage;
    this.backdropPath = backdropPath;
    this.releaseDate = releaseDate;



  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public boolean isAdult() {
    return adult;
  }

  public void setAdult(boolean adult) {
    this.adult = adult;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getOriginalTitle() {
    return originalTitle;
  }

  public void setOriginalTitle(String originalTitle) {
    this.originalTitle = originalTitle;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(int voteCount) {
    this.voteCount = voteCount;
  }

  public double getVoteAverage() {
    return voteAverage;
  }

  public void setVoteAverage(double voteAverage) {
    this.voteAverage = voteAverage;
  }

  public String getBackdropPath() {
    return backdropPath;
  }

  public void setBackdropPath(String backdropPath) {
    this.backdropPath = backdropPath;
  }

  public String getOriginalLanguage() {
    return originalLanguage;
  }

  public void setOriginalLanguage(String originalLanguage) {
    this.originalLanguage = originalLanguage;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(posterPath);
    dest.writeByte((byte) (adult ? 0x01 : 0x00));
    dest.writeString(overview);
    dest.writeString(originalTitle);
    dest.writeString(title);
    dest.writeInt(voteCount);
    dest.writeDouble(voteAverage);
    dest.writeString(backdropPath);
    dest.writeString(releaseDate);
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }
}