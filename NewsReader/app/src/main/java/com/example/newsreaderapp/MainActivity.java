package com.example.newsreaderapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsArticle>> {

    private ArrayList<NewsArticle> mNewsArticles = new ArrayList<>();
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search";
    private static final int ARTICLE_LOADER_ID = 1;
    private ArticleAdapter mArticleAdapter;
    private TextView mEmptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mArticleAdapter = new ArticleAdapter(MainActivity.this, mNewsArticles);
        ListView articleList = findViewById(R.id.news_article_list);

        articleList.setAdapter(mArticleAdapter);

        mEmptyTextView = findViewById(R.id.empty_view);

        articleList.setEmptyView(mEmptyTextView);

        ConnectivityManager commMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = commMgr.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(ARTICLE_LOADER_ID, null, this);
        } else {
            mEmptyTextView.setText(getString(R.string.no_internet));
        }

        articleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                String openIn = sharedPrefs.getString(getString(R.string.settings_open_in_key),
                        getString(R.string.settings_open_in_default));

                if (openIn.equals(getString(R.string.settings_open_in_app_value))) {
                    Intent openArticle = new Intent(MainActivity.this, ArticleDetailActivity.class);
                    openArticle.putExtra(getString(R.string.article), mNewsArticles.get(position));
                    startActivity(openArticle);
                } else {
                    Intent openArticle = new Intent(Intent.ACTION_VIEW);
                    openArticle.setData(Uri.parse(mNewsArticles.get(position).getURL()));
                    startActivity(openArticle);
                }
            }
        });
    }

    @NonNull
    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int id, @Nullable Bundle args) {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String resultsPerPage = sharedPrefs.getString(getString(R.string.settings_results_key),
                getString(R.string.settings_results_default));
        String searchQuery = sharedPrefs.getString(getString(R.string.settings_query_key),
                getString(R.string.settings_query_default));
        String orderBy = sharedPrefs.getString(getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default));

        Uri baseUri = Uri.parse(GUARDIAN_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter(getString(R.string.query_prefix), searchQuery);
        uriBuilder.appendQueryParameter(getString(R.string.tags_prefix), getString(R.string.tags_show));
        uriBuilder.appendQueryParameter(getString(R.string.api_prefix), getString(R.string.api_key));
        uriBuilder.appendQueryParameter(getString(R.string.order_prefix), orderBy);
        uriBuilder.appendQueryParameter(getString(R.string.results_prefix), resultsPerPage);

        return new ArticleLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<NewsArticle>> loader, List<NewsArticle> data) {

        mEmptyTextView.setText(getString(R.string.no_articles));

        if (data != null && !data.isEmpty()) {
            mArticleAdapter.clear();
            mArticleAdapter.addAll(data);
            mArticleAdapter.notifyDataSetChanged();
            mEmptyTextView.setVisibility(View.GONE);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<NewsArticle>> loader) {

        mArticleAdapter.clear();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
        } else if (id == R.id.action_refresh) {
            Intent refreshIntent = new Intent(this, MainActivity.class);
            startActivity(refreshIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
