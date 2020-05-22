package com.example.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "favourites")
public class Movie implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="movie_id")
    private final int movieID;
    @ColumnInfo(name = "movie_title")
    private final String title;
    @ColumnInfo(name="poster_path")
    private final String posterPath;
    @ColumnInfo(name = "movie_overview")
    private final String overview;
    @ColumnInfo(name="vote_average")
    private final float voteAverage;
    @ColumnInfo(name="release_date")
    private final String releaseDate;
    @ColumnInfo(name="runtime")
    private int runtime;
    @Ignore
    private List<Videos> trailers;
    @Ignore
    private List<Reviews> movieReviews;

    public Movie(int id, int movieID, String title, String posterPath, String overview, String releaseDate, float voteAverage) {
        this.id = id;
        this.movieID = movieID;
        this.title = title;
        this.posterPath= posterPath;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.runtime = 0;
        this.trailers = new ArrayList<>();
        this.movieReviews = new ArrayList<>();
    }

    @Ignore
    public Movie(int movieID, String title, String posterPath, String overview, String releaseDate, float voteAverage) {

        this.movieID = movieID;
        this.title = title;
        this.posterPath= posterPath;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.runtime = 0;
        this.trailers = new ArrayList<>();
        this.movieReviews = new ArrayList<>();
    }

    @Ignore
    public Movie(Movie movie) {
        this.movieID = movie.getMovieID();
        this.title = movie.getTitle();
        this.posterPath= movie.getPosterPath();
        this.overview = movie.getOverview();
        this.voteAverage = movie.getVoteAverage();
        this.releaseDate = movie.getReleaseDate();
        this.runtime = movie.getRuntime();
        this.trailers = movie.getTrailers();
        this.movieReviews = movie.getMovieReviews();
    }

    @Ignore
    protected Movie(Parcel in) {
        movieID = in.readInt();
        title = in.readString();
        posterPath = in.readString();
        overview = in.readString();
        voteAverage = in.readFloat();
        releaseDate = in.readString();
        runtime = in.readInt();
        if (in.readByte() == 0x01) {
            trailers = new ArrayList<>();
            in.readList(trailers, Videos.class.getClassLoader());
        } else {
            trailers = null;
        }
        if (in.readByte() == 0x01) {
            movieReviews = new ArrayList<>();
            in.readList(movieReviews, Reviews.class.getClassLoader());
        } else {
            movieReviews = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(movieID);
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeString(overview);
        dest.writeFloat(voteAverage);
        dest.writeString(releaseDate);
        dest.writeInt(runtime);
        if (trailers == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(trailers);
        }
        if (movieReviews == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(movieReviews);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getId() { return id; }

    public String getPosterPath() {
        return posterPath;
    }

    public int getMovieID() {
        return movieID;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() { return overview; }

    public String getReleaseDate() { return releaseDate; }

    public float getVoteAverage() { return voteAverage; }

    public int getRuntime() { return runtime; }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<Videos> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Videos> trailers) {
        this.trailers = trailers;
    }

    public List<Reviews> getMovieReviews() {
        return movieReviews;
    }

    public void setMovieReviews(List<Reviews> reviews) {
        this.movieReviews = reviews;
    }

}
