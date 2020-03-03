package com.example.newsreaderapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    final private static String LOG_TAG = QueryUtils.class.getSimpleName();

    public static List<NewsArticle> fetchArticlesListData(String requestUrl) {

        URL url = createUrl(requestUrl);

        String jsonresponse = null;

        try {
            jsonresponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making HTTP request", e);
        }

        List<NewsArticle> newsArticles = extractFeatureFromJson(jsonresponse);

        return newsArticles;

    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building URL", e);
        }

        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonresponse = "";

        if (url == null) {
            return jsonresponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonresponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the JSON results");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonresponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }

    private static List<NewsArticle> extractFeatureFromJson(String articlesJSON) {

        if (TextUtils.isEmpty(articlesJSON)) {
            return null;
        }

        List<NewsArticle> articles = new ArrayList<>();

        try {

            JSONObject baseJsonResponse = new JSONObject(articlesJSON);
            JSONArray articlesArray = baseJsonResponse.getJSONObject("response").getJSONArray("results");

            for (int i = 0; i < articlesArray.length(); i++) {
                JSONObject currentArticle = articlesArray.getJSONObject(i);
                JSONArray tags = currentArticle.getJSONArray("tags");

                String title = currentArticle.getString("webTitle");
                String url = currentArticle.getString("webUrl");
                String date = currentArticle.getString("webPublicationDate");
                String sectionID = currentArticle.getString("sectionId");

                String contributor = null;
                ArrayList<String> keywords = new ArrayList<>();
                if (tags.length() >= 1) {

                    StringBuilder authorTags = new StringBuilder();
                    JSONObject contributorTag;

                    for (int k = 0; k < tags.length(); k++) {
                        contributorTag = (JSONObject) tags.get(k);
                        if (contributorTag.getString("type").equals("contributor")) {
                            authorTags.append(contributorTag.getString("webTitle"));
                            if (k < tags.length() - 1) {
                                authorTags.append(", ");
                            }
                        } else {
                            keywords.add(contributorTag.getString("webTitle"));
                        }

                    }

                    contributor = authorTags.toString();
                }

                NewsArticle article = new NewsArticle(title, contributor, url, date, sectionID, keywords);
                articles.add(article);
                Log.i(LOG_TAG, article.getArticleDate().toString());
            }

        } catch (JSONException e) {

            Log.e(LOG_TAG, "Couldn't parse date: ", e);

        }

        return articles;
    }
}
