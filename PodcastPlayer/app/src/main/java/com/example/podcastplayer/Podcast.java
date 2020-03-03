package com.example.podcastplayer;

import java.io.Serializable;

public class Podcast implements Serializable {

    private String podcastTitle;
    private String podcastArtist;
    private String podcastDescription;
    private int podcastIconID;
    private boolean isAudiobook;
    private boolean isNowPlaying;

    public Podcast (String title, String artist, String description, int imageResourceID, boolean isAudio) {
        podcastTitle = title;
        podcastArtist = artist;
        podcastDescription = description;
        podcastIconID = imageResourceID;
        isAudiobook = isAudio;
    }

    public String getPodcastTitle() {
        return podcastTitle;
    }

    public String getPodcastArtist() {
        return podcastArtist;
    }

    public int getPodcastIcon() {
        return podcastIconID;
    }

    public boolean isAudioBook() { return isAudiobook; }

    public String getPodcastDescription() { return podcastDescription; }

    public void setPodcastDescription(String description) {
        podcastDescription = description;
    }

}
