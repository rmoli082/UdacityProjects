package com.example.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.adapters.ReviewsAdapter;
import com.example.popularmovies.adapters.TrailersAdapter;
import com.example.popularmovies.database.FavouritesDB;
import com.example.popularmovies.models.Movie;
import com.example.popularmovies.utilities.AppExecutors;
import com.example.popularmovies.utilities.NetworkUtils;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity implements TrailersAdapter.TrailerClickListener, ReviewsAdapter.ReviewClickListener {
    private FavouritesDB movieDB;
    private Movie currentMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ImageView moviePoster = findViewById(R.id.details_movie_poster);
        TextView titleText = findViewById(R.id.details_title_tv);
        TextView releaseDate = findViewById(R.id.details_release_tv);
        TextView synopsisText = findViewById(R.id.details_synopsis_tv);
        TextView ratingsText = findViewById(R.id.details_ratings_tv);
        TextView runtimeText = findViewById(R.id.details_runtime_tv);
        final ImageView favouriteButton = findViewById(R.id.favourite_button);
        movieDB = FavouritesDB.getInstance(this);

        Intent intent = getIntent();

        currentMovie = intent.getParcelableExtra(getString(R.string.current_movie));


        RecyclerView trailersRView = findViewById(R.id.details_trailers_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        trailersRView.setLayoutManager(layoutManager);
        TrailersAdapter mTrailerAdapter = new TrailersAdapter(this, this);
        mTrailerAdapter.setTrailers(currentMovie.getTrailers());
        trailersRView.setAdapter(mTrailerAdapter);

        RecyclerView reviewsRView = findViewById(R.id.details_reviews_rv);
        LinearLayoutManager reviewLayout = new LinearLayoutManager(this);
        reviewsRView.setLayoutManager(reviewLayout);
        ReviewsAdapter mReviewAdapter = new ReviewsAdapter(this, this);
        mReviewAdapter.setTrailers(currentMovie.getMovieReviews());
        reviewsRView.setAdapter(mReviewAdapter);


        final int currentMovieID = currentMovie.getMovieID();

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (movieDB.movieDao().checkFavourite(currentMovieID) != null) {
                    favouriteButton.setImageResource(android.R.drawable.star_big_on);
                }
            }
        });

        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (movieDB.movieDao().checkFavourite(currentMovieID) == null) {

                            movieDB.movieDao().insertFavourite(currentMovie);

                            favouriteButton.setImageResource(android.R.drawable.star_big_on);

                            Snackbar.make(v, getString(R.string.snackbar_add), Snackbar.LENGTH_LONG).show();
                        } else {

                            movieDB.movieDao().removeFavourite(movieDB.movieDao().checkFavourite(currentMovieID));

                            favouriteButton.setImageResource(android.R.drawable.star_big_off);

                            Snackbar.make(v, getString(R.string.snackbar_delete), Snackbar.LENGTH_LONG).show();
                        }
                    }
                });



            }
        });

        assert currentMovie != null;
        Picasso.get().load(NetworkUtils.buildPosterURL(currentMovie.getPosterPath()))
                .placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground)
                .into(moviePoster);

        setTitle(" ");

        titleText.setText(currentMovie.getTitle());
        String[] tokens = currentMovie.getReleaseDate().split("-");
        String year = tokens[0];
        releaseDate.setText(year);
        synopsisText.setText(currentMovie.getOverview());
        ratingsText.setText(String.valueOf(currentMovie.getVoteAverage()));
        ratingsText.append(getText(R.string.ratings_outta10));
        runtimeText.setText(String.valueOf(currentMovie.getRuntime()));
        runtimeText.append(getString(R.string.minutes_suffix));

    }

    @Override
    public void onTrailerClickListener(String itemKey) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + itemKey));
        startActivity(intent);
    }

    @Override
    public void onReviewClickListener(String reviewURL) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(reviewURL));
        startActivity(intent);
    }
}
