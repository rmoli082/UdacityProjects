package com.example.popularmovies.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.popularmovies.models.Movie;

import java.util.List;

@Dao
public interface MovieDAO {

    @Query("SELECT * FROM favourites")
    LiveData<List<Movie>> loadAllMovies();

    @Insert
    void insertFavourite(Movie movie);

    @Delete
    void removeFavourite(Movie movie);

    @Query("SELECT * FROM favourites WHERE movie_id = :movieId")
    Movie checkFavourite(int movieId);

}
