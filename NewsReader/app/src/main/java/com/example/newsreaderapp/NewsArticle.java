package com.example.newsreaderapp;

import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NewsArticle implements Serializable {

    private String mArticleTitle;
    private String mArticleUrl;
    private String mArticleAuthor;
    private String mArticleDate;
    private String mSectionID;
    private ArrayList<String> mKeywords;
    final private static String LOG_TAG = NewsArticle.class.getSimpleName();

    public NewsArticle(String title, String contributor, String url, String date,
                       String sectionID, ArrayList<String> keywords) {
        mArticleTitle = title;
        mArticleUrl = url;
        mArticleDate = date;
        mSectionID = sectionID;
        mArticleAuthor = contributor;
        mKeywords = keywords;
    }

    public String getArticleTitle() {
        return mArticleTitle;
    }

    public String getArticleAuthor() {
        return mArticleAuthor;
    }

    public String getSectionID() {
        return mSectionID;
    }

    public Date getArticleDate() {

        String pattern = "yyyy-MM-dd'T'HH:mm:ssZ";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.CANADA);

        Date date = new Date();

        try {
            date = dateFormat.parse(mArticleDate.replaceAll("Z$", "+0000"));
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Couldn't parse date", e);
        }


        return date;
    }

    public String getKeywords() {

        StringBuilder keywords = new StringBuilder();

        for (int i = 0; i < mKeywords.size(); i++) {
            keywords.append(mKeywords.get(i));
            if (i < mKeywords.size() - 1) {
                keywords.append(", ");
            }
        }

        return keywords.toString();
    }

    public String getURL() {
        return mArticleUrl;
    }

}
