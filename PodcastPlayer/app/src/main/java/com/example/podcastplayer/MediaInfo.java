package com.example.podcastplayer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MediaInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_media_info);

        Intent intent = getIntent();
        Podcast podcast = (Podcast) intent.getSerializableExtra("podcast");

        TextView mediaTitle = findViewById(R.id.media_song_title);
        TextView mediaArtist = findViewById(R.id.media_song_artist);
        TextView mediaDescription = findViewById(R.id.podcast_description);
        ImageView mediaIcon = findViewById(R.id.media_song_cover);

        mediaTitle.setText(podcast.getPodcastTitle());
        mediaArtist.setText(podcast.getPodcastArtist());
        mediaIcon.setImageResource(podcast.getPodcastIcon());
        mediaDescription.setText(podcast.getPodcastDescription());
    }
}
