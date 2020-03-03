package com.example.tourguideactivity;

import java.io.Serializable;

public abstract class Attraction implements Serializable {

    private String mAttractionName;
    private String mAttractionNeighbourhood;
    private String mAttractionAddress;
    private String mAttractionDescription;
    private String mAttractionWebsite;
    private String mAttractionPhoneNumber;
    private int mImageResourceID;

    public Attraction (String name, String neighbourhood, String address, String description,
                       String website, String number, int image) {
        mAttractionName = name;
        mAttractionNeighbourhood = neighbourhood;
        mAttractionAddress = address;
        mAttractionDescription = description;
        mAttractionWebsite = website;
        mAttractionPhoneNumber = number;
        mImageResourceID = image;
    }

    public String getAttractionName() {
        return mAttractionName;
    }

    public String getAttractionNeighbourhood() {
        return mAttractionNeighbourhood;
    }

    public String getAttractionAddress() {
        return mAttractionAddress;
    }

    public String getAttractionDescription() {
        return mAttractionDescription;
    }

    public String getAttractionWebsite() {
        return mAttractionWebsite;
    }

    public String getAttractionPhoneNumber() {
        return mAttractionPhoneNumber;
    }

    public int getImageResourceID() {
        return mImageResourceID;
    }

    public abstract String getExtra();
}
