package com.example.popularmovies.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.popularmovies.models.Movie;

@Database(entities = {Movie.class}, version = 2, exportSchema = false)
public abstract class FavouritesDB extends RoomDatabase {

    private static final String DATABASE_NAME = "movieFavourites";
    private static final Object LOCK = new Object();
    private static FavouritesDB sInstance;

    public static FavouritesDB getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(), FavouritesDB.class, FavouritesDB.DATABASE_NAME)
                        .fallbackToDestructiveMigration().build();
            }
        }
        return sInstance;
    }

    public abstract MovieDAO movieDao();

}
