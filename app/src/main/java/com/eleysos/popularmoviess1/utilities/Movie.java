package com.eleysos.popularmoviess1.utilities;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private int id;
    private String title;
    private String originalTitle;
    private String posterPath;
    private String overview;
    private double vote_average;
    private String releaseDate;

    public Movie(int id, String title, String originalTitle, String posterPath, String overview, double vote_average, String releaseDate) {
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.overview = overview;
        this.vote_average = vote_average;
        this.releaseDate = releaseDate;
    }


    public Movie(Parcel parcel){
        setId(parcel.readInt());
        setTitle(parcel.readString());
        setOriginalTitle(parcel.readString());
        setPosterPath(parcel.readString());
        setOverview(parcel.readString());
        setVote_average(parcel.readDouble());
        setReleaseDate(parcel.readString());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getId());
        parcel.writeString(getTitle());
        parcel.writeString(getOriginalTitle());
        parcel.writeString(getPosterPath());
        parcel.writeString(getOverview());
        parcel.writeDouble(getVote_average());
        parcel.writeString(getReleaseDate());
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}