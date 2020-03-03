package com.example.podcastplayer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Podcast> podcasts;
    private Podcast currentPodcast;
    private PodcastAdapter podcastAdapter;
    private ListView podcastList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        podcasts = new ArrayList<>();
        currentPodcast = new Podcast("Chimichangas", "Food For Thought", getString(R.string.description), R.drawable.headphone, false);
        podcasts.add(currentPodcast);
        podcasts.add(new Podcast("The Lord of the Rings: Fellowship of the Ring", "JRR Tolkein", getString(R.string.description2), R.drawable.audiobook, true));

        loadPodcasts();

        podcastList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                currentPodcast = podcasts.get(position);
                TextView nowPlayingTitle = findViewById(R.id.now_playing_title);
                TextView nowPlayingArtist = findViewById(R.id.now_playing_artist);
                ImageView nowPlayingImage = findViewById(R.id.now_playing_cover);
                nowPlayingTitle.setText(String.valueOf(currentPodcast.getPodcastTitle()));
                nowPlayingArtist.setText(String.valueOf(currentPodcast.getPodcastArtist()));
                nowPlayingImage.setImageResource(currentPodcast.getPodcastIcon());
            }
        });

        podcastList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mediaInfo = new Intent(MainActivity.this, MediaInfo.class);
                mediaInfo.putExtra("podcast", podcasts.get(position));
                startActivity(mediaInfo);
                return true;
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addMedia = new Intent(MainActivity.this, AddMedia.class);
                addMedia.putExtra("array", podcasts);
                startActivityForResult(addMedia, 1);
            }
        });

        RelativeLayout nowPlayingBar = findViewById(R.id.now_playing_banner);
        nowPlayingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNowPlaying();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK && data != null) {
                    podcasts = (ArrayList<Podcast>) data.getSerializableExtra("podcast");
                    loadPodcasts();
                }
        }
    }

    private void loadPodcasts() {
        podcastAdapter = new PodcastAdapter(this, podcasts);
        podcastList = findViewById(R.id.podcast_list);
        podcastList.setAdapter(podcastAdapter);
    }

    private void loadNowPlaying() {
        Intent playNow = new Intent(MainActivity.this, NowPlaying.class);
        playNow.putExtra("podcast", currentPodcast);
        startActivity(playNow);
    }

}
