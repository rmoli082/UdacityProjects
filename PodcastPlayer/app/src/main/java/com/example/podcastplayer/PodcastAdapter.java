package com.example.podcastplayer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PodcastAdapter extends ArrayAdapter<Podcast> {

    public PodcastAdapter (Activity context, ArrayList<Podcast> podcasts) {
        super(context, 0, podcasts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View gridItemView = convertView;
        if (gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.media_list, parent, false);
        }

        Podcast currentPodcast = getItem(position);

        ImageView podcastImage = gridItemView.findViewById(R.id.podcast_image);
        podcastImage.setImageResource(currentPodcast.getPodcastIcon());

        TextView podcastTitle = gridItemView.findViewById(R.id.podcast_title);
        podcastTitle.setText(currentPodcast.getPodcastTitle());

        TextView podcastArtist = gridItemView.findViewById(R.id.podcast_artist);
        podcastArtist.setText(currentPodcast.getPodcastArtist());

        return gridItemView;
    }
}
