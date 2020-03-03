package com.example.podcastplayer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NowPlaying extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_now_playing);
        TextView nowPlayingTitle = findViewById(R.id.song_title);
        TextView nowPlayingArtist = findViewById(R.id.song_artist);
        ImageView nowPlayingIcon = findViewById(R.id.song_icon);
        Intent intent = getIntent();
        Podcast podcast = (Podcast) intent.getSerializableExtra("podcast");
        nowPlayingTitle.setText(podcast.getPodcastTitle());
        nowPlayingArtist.setText(podcast.getPodcastArtist());
        nowPlayingIcon.setImageResource(podcast.getPodcastIcon());
        if (podcast.isAudioBook()) {
            ImageView prevChapter = findViewById(R.id.previous_chapter);
            ImageView nextChapter = findViewById(R.id.next_chapter);
            prevChapter.setVisibility(View.VISIBLE);
            nextChapter.setVisibility(View.VISIBLE);
        }
    }
}
