package com.example.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Videos implements Parcelable {

    private final String name;
    private final String site;
    private final String key;

    public Videos() {
        this.name = "";
        this.site = "";
        this.key = "";
    }

    public Videos(String name, String site, String key) {
        this.name = name;
        this.site = site;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    Videos(Parcel in) {
        name = in.readString();
        site = in.readString();
        key = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(site);
        dest.writeString(key);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Videos> CREATOR = new Parcelable.Creator<Videos>() {
        @Override
        public Videos createFromParcel(Parcel in) {
            return new Videos(in);
        }

        @Override
        public Videos[] newArray(int size) {
            return new Videos[size];
        }
    };

}
