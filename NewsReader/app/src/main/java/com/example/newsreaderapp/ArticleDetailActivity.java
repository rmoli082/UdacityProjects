package com.example.newsreaderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ArticleDetailActivity extends AppCompatActivity {

    private WebView mWebView;
    private NewsArticle currentArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        currentArticle = (NewsArticle) getIntent().getSerializableExtra(getString(R.string.article));

        mWebView = findViewById(R.id.webview);
        WebSettings settings = mWebView.getSettings();

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });

        mWebView.loadUrl(currentArticle.getURL());
    }
}
