package com.example.tourguideactivity;

public class TouristSpot extends Attraction {

    public TouristSpot(String name, String neighbourhood, String address, String description,
                       String website, String number, int image) {
        super(name, neighbourhood, address, description, website, number, image);
    }

    @Override
    public String getExtra() {
        return null;
    }

}
