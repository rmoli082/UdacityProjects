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
public class PlacesToGoFragment extends Fragment {

    private ArrayList<Attraction> mTouristSpots = new ArrayList<>();


    public PlacesToGoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        TextView mainHeader = rootView.findViewById(R.id.list_header_text);
        mainHeader.setText(getString(R.string.places_go));

        loadAttractions();

        ListView touristSpotList = rootView.findViewById(R.id.item_list);
        AttractionAdapter adapter = new AttractionAdapter(getActivity(), mTouristSpots);
        touristSpotList.setAdapter(adapter);

        touristSpotList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent getDetails = new Intent (getActivity(), AttractionDetailFragment.class);
                getDetails.putExtra(getString(R.string.detail), mTouristSpots.get(position));
                getDetails.putExtra(getString(R.string.type), getString(R.string.tourist));
                startActivity(getDetails);
            }
        });

        return rootView;
    }

    private void loadAttractions() {

        mTouristSpots.clear();

        mTouristSpots.add(new TouristSpot(getString(R.string.cn_tower), getString(R.string.downtown), getString(R.string.cntower_address), getString(R.string.cntower_description), getString(R.string.cntower_website), getString(R.string.cntower_number), R.drawable.cn_tower));
        mTouristSpots.add(new TouristSpot(getString(R.string.casa_loma), getString(R.string.downtown), getString(R.string.casaloma_address), getString(R.string.casaloma_description), getString(R.string.casaloma_website), getString(R.string.casaloma_number), R.drawable.casa_loma));
        mTouristSpots.add(new TouristSpot(getString(R.string.zoo), getString(R.string.scarborough), getString(R.string.zoo_address), getString(R.string.zoo_description), getString(R.string.zoo_website), getString(R.string.zoo_number), R.drawable.toronto_zoo));

    }

}
