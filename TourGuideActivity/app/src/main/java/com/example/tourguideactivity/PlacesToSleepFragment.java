package com.example.tourguideactivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlacesToSleepFragment extends Fragment {

    private ArrayList<Attraction> mHotels = new ArrayList<>();

    public PlacesToSleepFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        TextView mainHeader = rootView.findViewById(R.id.list_header_text);
        mainHeader.setText(getString(R.string.places_sleep));

        loadAttractions();

        ListView hotelList = rootView.findViewById(R.id.item_list);
        AttractionAdapter adapter = new AttractionAdapter(getActivity(), mHotels);
        hotelList.setAdapter(adapter);

        hotelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent getDetails = new Intent (getActivity(), AttractionDetailFragment.class);
                getDetails.putExtra(getString(R.string.detail), mHotels.get(position));
                getDetails.putExtra(getString(R.string.type), getString(R.string.hotel));
                startActivity(getDetails);
            }
        });

        return rootView;
    }

    private void loadAttractions() {

        mHotels.clear();

        Hotel test = new Hotel(getString(R.string.royal_york), getString(R.string.downtown), getString(R.string.ry_address), getString(R.string.ry_description), getString(R.string.ry_website), getString(R.string.ry_number), R.drawable.royal_york_hotel);
        test.setStarRating(5.0);
        Hotel test2 = new Hotel(getString(R.string.shangrila), getString(R.string.downtown), getString(R.string.shangrila_address), getString(R.string.shangrila_description), getString(R.string.shangrila_website), getString(R.string.shangrila_number), R.drawable.shangri_la_hotel);
        test2.setStarRating(5.0);
        Hotel test3 = new Hotel(getString(R.string.marriott), getString(R.string.etobicoke), getString(R.string.marriott_address), getString(R.string.marriott_description), getString(R.string.marriott_website), getString(R.string.marriott_number), R.drawable.marriot_airport_hotel);
        test3.setStarRating(4.0);

        mHotels.add(test);
        mHotels.add(test2);
        mHotels.add(test3);
    }

}
