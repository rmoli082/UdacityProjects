package com.example.tourguideactivity;

public class Restaurant extends Attraction {

    private String mCuisineType;

    public Restaurant(String name, String neighbourhood, String address, String description,
                      String website, String number, int image) {
        super(name, neighbourhood, address, description, website, number, image);
    }

    @Override
    public String getExtra() {
        return mCuisineType;
    }

    public void setCuisineType(String cuisineType) {
        mCuisineType = cuisineType;
    }

}
