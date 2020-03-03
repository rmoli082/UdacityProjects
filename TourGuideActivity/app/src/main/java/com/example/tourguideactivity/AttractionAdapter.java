package com.example.tourguideactivity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AttractionAdapter extends ArrayAdapter<Attraction> {


    public AttractionAdapter(Activity context, ArrayList<Attraction> attraction) {
        super(context, 0, attraction);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }

        Attraction currentAttraction = getItem(position);

        ImageView attractionImage = listItemView.findViewById(R.id.attraction_image);
        attractionImage.setImageResource(currentAttraction.getImageResourceID());

        TextView attractionName = listItemView.findViewById(R.id.attraction_name);
        attractionName.setText(String.valueOf(currentAttraction.getAttractionName()));

        TextView attractionNeighbourhood = listItemView.findViewById(R.id.attraction_neighbourhood);
        attractionNeighbourhood.setText(String.valueOf(currentAttraction.getAttractionNeighbourhood()));

        TextView attractionExtra = listItemView.findViewById(R.id.attraction_extra);
        attractionExtra.setText(currentAttraction.getExtra());

        return listItemView;
    }
}
