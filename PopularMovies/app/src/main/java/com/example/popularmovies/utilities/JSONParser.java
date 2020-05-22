package com.example.popularmovies.utilities;

import com.example.popularmovies.models.Movie;
import com.example.popularmovies.models.Reviews;
import com.example.popularmovies.models.Videos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {


    public static ArrayList<Movie> getMovieDataFromJSON (String jsonData) throws JSONException {

        ArrayList<Movie> movieList = new ArrayList<>();

        JSONObject moviesListings = new JSONObject(jsonData);
        JSONArray moviesArray = moviesListings.getJSONArray("results");

        for (int i = 0; i < moviesArray.length(); i++) {

            JSONObject movieData = moviesArray.getJSONObject(i);

            int movieID = movieData.getInt("id");
            String movieTitle = movieData.getString("original_title");
            String posterPath = movieData.getString("poster_path");
            String overview = movieData.getString("overview");
            String releaseDate = movieData.getString("release_date");
            float userRating = (float)movieData.getDouble("vote_average");


            Movie movie = new Movie(movieID, movieTitle, posterPath, overview, releaseDate, userRating);

            movie.setRuntime(getMovieRuntime(movie));
            movie.setTrailers(getMovieTrailers(movie));
            movie.setMovieReviews(getMovieReviews(movie));

            movieList.add(movie);
        }

        return movieList;

    }

    private static int getMovieRuntime(Movie movie) throws JSONException {

        String jsondata = null;

        try {
            jsondata = NetworkUtils.getResponseFromURL(NetworkUtils.buildMovieInfoURL(movie.getMovieID()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject movieJSON = new JSONObject(jsondata);

        return movieJSON.getInt("runtime");
    }

    private static List<Videos> getMovieTrailers(Movie movie) throws JSONException {

        String jsondata = null;

        try {
            jsondata = NetworkUtils.getResponseFromURL(NetworkUtils.buildVideoLinksURL(movie.getMovieID()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject movieJSON = new JSONObject(jsondata);
        JSONArray videoArray = movieJSON.getJSONArray("results");
        ArrayList<Videos> videoPaths = new ArrayList<>();

        for (int i = 0; i < videoArray.length(); i++) {
            JSONObject videos = videoArray.getJSONObject(i);
            String videoKey = videos.getString("key");
            String videoName = videos.getString("name");
            String videoSite = videos.getString("site");
            Videos video = new Videos(videoName, videoSite, videoKey);
            videoPaths.add(video);
        }

        return videoPaths;
    }

    private static List<Reviews> getMovieReviews(Movie movie) throws JSONException {

        String jsondata = null;

        try {
            jsondata = NetworkUtils.getResponseFromURL(NetworkUtils.buildReviewLinksURL(movie.getMovieID()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject movieJSON = new JSONObject(jsondata);
        JSONArray reviewArray = movieJSON.getJSONArray("results");
        ArrayList<Reviews> movieReviews = new ArrayList<>();

        for (int i = 0; i < reviewArray.length(); i++) {
            JSONObject reviews = reviewArray.getJSONObject(i);
            String reviewAuthor = reviews.getString("author");
            String reviewContent = reviews.getString("content");
            String reviewUrl = reviews.getString("url");

            Reviews review = new Reviews(reviewAuthor, reviewContent, reviewUrl);

            movieReviews.add(review);
        }

        return movieReviews;
    }
}
