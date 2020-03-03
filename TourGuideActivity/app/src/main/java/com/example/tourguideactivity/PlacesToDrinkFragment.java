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
public class PlacesToDrinkFragment extends Fragment {

    private ArrayList<Attraction> mBars = new ArrayList<>();

    public PlacesToDrinkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        TextView mainHeader = rootView.findViewById(R.id.list_header_text);
        mainHeader.setText(getString(R.string.places_drink));

        loadAttractions();

        ListView restaurantList = rootView.findViewById(R.id.item_list);
        AttractionAdapter adapter = new AttractionAdapter(getActivity(), mBars);
        restaurantList.setAdapter(adapter);

        restaurantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent getDetails = new Intent (getActivity(), AttractionDetailFragment.class);
                getDetails.putExtra(getString(R.string.detail), mBars.get(position));
                getDetails.putExtra(getString(R.string.type), getString(R.string.restaurant));
                startActivity(getDetails);
            }
        });

        return rootView;
    }

    private void loadAttractions() {

        mBars.clear();

        Restaurant test = new Restaurant(getString(R.string.bar_raval), getString(R.string.downtown), getString(R.string.barraval_address), getString(R.string.barraval_description), getString(R.string.barraval_website), getString(R.string.barraval_number), R.drawable.bar_raval);
        test.setCuisineType(getString(R.string.lounge));
        Restaurant test2 = new Restaurant(getString(R.string.time_capsule), getString(R.string.east_york), getString(R.string.tc_address), getString(R.string.tc_description), getString(R.string.tc_website), getString(R.string.tc_number), R.drawable.time_capsule);
        test2.setCuisineType(getString(R.string.gamebar));
        Restaurant test3 = new Restaurant(getString(R.string.aviary), getString(R.string.downtown), getString(R.string.aviary_address), getString(R.string.aviary_description), getString(R.string.aviary_website), getString(R.string.aviary_number), R.drawable.aviary);
        test3.setCuisineType(getString(R.string.brewpub));

        mBars.add(test);
        mBars.add(test2);
        mBars.add(test3);
    }

}
