package com.example.tourguideactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AttractionDetailFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_detail);

        Intent intent = getIntent();
        final Attraction currentSpot;

        if (intent.getStringExtra(getString(R.string.type)).equals(getString(R.string.tourist))) {
            currentSpot = (TouristSpot) intent.getSerializableExtra(getString(R.string.detail));
        } else if (intent.getStringExtra(getString(R.string.type)).equals(getString(R.string.restaurant))){
            currentSpot = (Restaurant) intent.getSerializableExtra(getString(R.string.detail));
        } else {
            currentSpot = (Hotel) intent.getSerializableExtra(getString(R.string.detail));
        }


        ImageView attractionImage = findViewById(R.id.detail_attraction_image);
        attractionImage.setImageResource(currentSpot.getImageResourceID());

        TextView attractionName = findViewById(R.id.detail_attraction_name);
        attractionName.setText(currentSpot.getAttractionName());

        TextView attractionDetail = findViewById(R.id.detail_attraction_description);
        attractionDetail.setText(currentSpot.getAttractionDescription());

        TextView callButton = findViewById(R.id.call);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = currentSpot.getAttractionPhoneNumber();
                Intent callAttraction = new Intent(Intent.ACTION_DIAL, Uri.parse(getString(R.string.call_prefix) + number));
                startActivity(callAttraction);
            }
        });

        TextView websiteButton = findViewById(R.id.website);
        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String websiteURL = currentSpot.getAttractionWebsite();
                Intent websiteAttraction = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteURL));
                startActivity(websiteAttraction);
            }
        });

        TextView directionsButton = findViewById(R.id.directions);
        directionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = currentSpot.getAttractionAddress();
                Intent directionsAttraction = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.directions_prefix) + address));
                startActivity(directionsAttraction);
            }
        });
    }
}
