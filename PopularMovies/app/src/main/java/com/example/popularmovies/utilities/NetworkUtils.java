package com.example.popularmovies.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String MOVIES_BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String IMAGES_BASE_URL = "https://image.tmdb.org/t/p/w300";
    private static final String POPULAR_MOVIE_PARAM = "popular?api_key=";
    private static final String TOP_RATED_MOVIE_PARAM = "top_rated?api_key=";
    private static final String VIDEOS_PARAM = "/videos?api_key=";
    private static final String REVIEWS_PARAM = "/reviews?api_key=";
    private static final String API_KEY_PARAM = "?api_key=";
    private static final String API_KEY = "38d769c5b58a498f48398ab245507311";

    public static URL buildPopularMoviesURL() {
        URL popularURL = null;

        try {
            popularURL = new URL(MOVIES_BASE_URL + POPULAR_MOVIE_PARAM + API_KEY);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return popularURL;
    }

    public static URL buildTopRatedMoviesURL() {

        URL topRatedURL = null;

        try {
            topRatedURL = new URL(MOVIES_BASE_URL + TOP_RATED_MOVIE_PARAM + API_KEY);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return topRatedURL;
    }

    public static Uri buildPosterURL (String posterPath) {

        return Uri.parse(IMAGES_BASE_URL + posterPath);
    }

    public static URL buildMovieInfoURL(int movieID){
        URL movieInfoURL = null;

        try {
            movieInfoURL = new URL(MOVIES_BASE_URL + movieID + API_KEY_PARAM  + API_KEY);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return movieInfoURL;
    }

    public static URL buildVideoLinksURL(int movieID){
        URL movieVideoURL = null;

        try {
            movieVideoURL = new URL(MOVIES_BASE_URL + movieID + VIDEOS_PARAM + API_KEY);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return movieVideoURL;
    }

    public static URL buildReviewLinksURL(int movieID){
        URL movieVideoURL = null;

        try {
            movieVideoURL = new URL(MOVIES_BASE_URL + movieID + REVIEWS_PARAM + API_KEY);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return movieVideoURL;
    }

    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream iStream = urlConnection.getInputStream();

            Scanner scanner = new Scanner(iStream);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }

    }

}
