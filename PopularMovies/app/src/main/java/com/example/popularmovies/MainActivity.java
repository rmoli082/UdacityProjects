package com.example.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.adapters.MoviesAdapter;
import com.example.popularmovies.models.MainViewModel;
import com.example.popularmovies.models.Movie;
import com.example.popularmovies.utilities.JSONParser;
import com.example.popularmovies.utilities.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.ListItemClickListener {

    private RecyclerView mRecyclerView;
    private MoviesAdapter mMoviesAdapter;
    private ProgressBar mLoadingBar;
    private TextView mErrorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences mSharedPrefs = getSharedPreferences(getString(R.string.prefs), MODE_PRIVATE);
        mLoadingBar = findViewById(R.id.loading_progressbar);

        mRecyclerView = findViewById(R.id.movies_rv);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(layoutManager);
        mMoviesAdapter = new MoviesAdapter(this);
        mRecyclerView.setAdapter(mMoviesAdapter);

        mErrorText = findViewById(R.id.error_text);

        String searchPref = mSharedPrefs.getString(getString(R.string.search_type), getString(R.string.search_pop));
        loadMoviesData(searchPref);


        if (searchPref.equals(getString(R.string.search_pop))){
            setTitle(R.string.popular);
        } else if (searchPref.equals(getString(R.string.search_top))){
            setTitle(R.string.top_rated);
        } else {
            setTitle(R.string.favourites);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        SharedPreferences sh = getSharedPreferences(getString(R.string.prefs), MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sh.edit();

        switch (id)
        {
            case (R.id.popular_movies):
                loadMoviesData(getString(R.string.search_pop));
                setTitle(R.string.app_name);
                prefsEditor.putString(getString(R.string.search_type), getString(R.string.search_pop));
                prefsEditor.apply();
                return true;
            case (R.id.top_movies):
                loadMoviesData(getString(R.string.search_top));
                setTitle(R.string.top_rated);
                prefsEditor.putString(getString(R.string.search_type), getString(R.string.search_top));
                prefsEditor.apply();
                return true;
            case (R.id.favourites):
                loadMoviesData(getString(R.string.search_fave));
                setTitle(R.string.favourites);
                prefsEditor.putString(getString(R.string.search_type), getString(R.string.search_fave));
                prefsEditor.apply();
        }

        return false;
    }

    private void loadMoviesData(String preference) {
        if (preference.equals(getString(R.string.search_pop)) || preference.equals(getString(R.string.search_top))) {
            new FetchMoviesTask().execute(preference);
        } else if (preference.equals(getString(R.string.search_fave))) {
            retrieveFavourites();
        }
    }

    private void retrieveFavourites() {
        mLoadingBar.setVisibility(View.INVISIBLE);
        showMovieData();

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                mMoviesAdapter.setMoviesList(movies);
            }
        });
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorText.setVisibility(View.VISIBLE);
    }

    private void showMovieData() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onListItemClick(int position) {
        List<Movie> movies = mMoviesAdapter.getMoviesList();
        Movie currentMovie = movies.get(position);

        Intent detailsView = new Intent(MainActivity.this, DetailsActivity.class);
        detailsView.putExtra(getString(R.string.current_movie), currentMovie);
        startActivity(detailsView);
    }

    class FetchMoviesTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        @Override
        protected void onPreExecute() {
            mLoadingBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Movie> doInBackground(String... strings) {

            URL listURL = null;

            if (strings.length == 0)
                return null;

            String preference = strings[0];

            if (preference.equals(getString(R.string.search_pop))){
                listURL = NetworkUtils.buildPopularMoviesURL();
            } else if (preference.equals(getString(R.string.search_top))){
                listURL = NetworkUtils.buildTopRatedMoviesURL();
            }

            try {
                String movieList = NetworkUtils.getResponseFromURL(listURL);
                return JSONParser.getMovieDataFromJSON(movieList);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            mLoadingBar.setVisibility(View.INVISIBLE);

            if (movies != null) {
                mMoviesAdapter.setMoviesList(movies);
                showMovieData();
            } else {
                showErrorMessage();
            }
        }
    }
}
