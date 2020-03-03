package com.example.tourguideactivity;

public class Hotel extends Attraction {

    private double mStarRating;

    public Hotel(String name, String neighbourhood, String address, String description,
                 String website, String number, int image) {
        super(name, neighbourhood, address, description, website, number, image);
    }

    @Override
    public String getExtra() {
        return mStarRating + " stars";
    }

    public void setStarRating(double rating) {
        mStarRating = rating;
    }

}
