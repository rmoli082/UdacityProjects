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
public class PlacesToEatFragment extends Fragment {

    private ArrayList<Attraction> mRestaurants = new ArrayList<>();

    public PlacesToEatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        TextView mainHeader = rootView.findViewById(R.id.list_header_text);
        mainHeader.setText(getString(R.string.places_eat));

        loadAttractions();

        ListView restaurantList = rootView.findViewById(R.id.item_list);
        AttractionAdapter adapter = new AttractionAdapter(getActivity(), mRestaurants);
        restaurantList.setAdapter(adapter);

        restaurantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent getDetails = new Intent (getActivity(), AttractionDetailFragment.class);
                getDetails.putExtra(getString(R.string.detail), mRestaurants.get(position));
                getDetails.putExtra(getString(R.string.type), getString(R.string.restaurant));
                startActivity(getDetails);
            }
        });

        return rootView;
    }

    private void loadAttractions() {

        mRestaurants.clear();

        Restaurant test = new Restaurant(getString(R.string.kingyo), getString(R.string.downtown), getString(R.string.kingyo_address), getString(R.string.kingyo_description), getString(R.string.kingyo_website), getString(R.string.kingyo_number), R.drawable.kingyo);
        test.setCuisineType(getString(R.string.japanese));
        Restaurant test2 = new Restaurant(getString(R.string.remezzo), getString(R.string.scarborough), getString(R.string.remezzo_address), getString(R.string.remezzo_description), getString(R.string.remezzo_website), getString(R.string.remezzo_number), R.drawable.remezzo);
        test2.setCuisineType(getString(R.string.italian));
        Restaurant test3 = new Restaurant(getString(R.string.ruths_chris), getString(R.string.downtown), getString(R.string.rc_address), getString(R.string.rc_description), getString(R.string.rc_website), getString(R.string.rc_number), R.drawable.ruths_chris);
        test3.setCuisineType(getString(R.string.steakhouse));

        mRestaurants.add(test);
        mRestaurants.add(test2);
        mRestaurants.add(test3);
    }

}
