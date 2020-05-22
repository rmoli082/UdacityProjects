package com.example.popularmovies.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.popularmovies.database.FavouritesDB;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private final LiveData<List<Movie>> movies;

    public MainViewModel(@NonNull Application application) {
        super(application);
        FavouritesDB database = FavouritesDB.getInstance(this.getApplication());
        movies = database.movieDao().loadAllMovies();
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

}
