package com.example.newsreaderapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ArticleAdapter extends ArrayAdapter<NewsArticle> {

    public ArticleAdapter(Activity context, ArrayList<NewsArticle> articles) {
        super(context, 0, articles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (convertView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        }

        NewsArticle currentArticle = getItem(position);

        TextView sectionIdText = listItemView.findViewById(R.id.news_item_sectionID);
        sectionIdText.setText(currentArticle.getSectionID());

        TextView titleText = listItemView.findViewById(R.id.news_item_title);
        titleText.setText(currentArticle.getArticleTitle());

        TextView authorText = listItemView.findViewById(R.id.news_item_author);
        authorText.setText(currentArticle.getArticleAuthor());

        TextView dateText = listItemView.findViewById(R.id.news_item_date);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMM yyy HH:mm:ss z", Locale.CANADA);
        dateText.setText(sdf.format(currentArticle.getArticleDate()));

        TextView keywordsText = listItemView.findViewById(R.id.news_item_keywords);
        keywordsText.setText(currentArticle.getKeywords());

        return listItemView;
    }
}
