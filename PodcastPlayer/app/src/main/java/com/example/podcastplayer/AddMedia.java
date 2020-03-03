package com.example.podcastplayer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddMedia extends AppCompatActivity {

    private ArrayList<Podcast> podcasts;
    private String title;
    private String artist;
    private String description;
    private boolean isAudiobook;
    private int coverImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_add_media);

        Intent intent = getIntent();
        podcasts = (ArrayList<Podcast>) intent.getSerializableExtra("array");

        Button addMediaButton = findViewById(R.id.submit_media_button);

        addMediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText titleEntry = findViewById(R.id.add_title_entry);
                title = String.valueOf(titleEntry.getText());
                EditText artistEntry = findViewById(R.id.add_artist_entry);
                artist = String.valueOf(artistEntry.getText());
                EditText descriptionEntry = findViewById(R.id.add_description_entry);
                description = String.valueOf(descriptionEntry.getText());
                CheckBox AudiobookEntry = findViewById(R.id.is_audiobook_check);
                isAudiobook = AudiobookEntry.isChecked();
                RadioGroup rg = findViewById(R.id.add_cover_group);
                RadioButton rb = findViewById(rg.getCheckedRadioButtonId());
                if (rg.getCheckedRadioButtonId() == R.id.select_audiobook) {
                    coverImage = R.drawable.audiobook;
                } else {
                    coverImage = R.drawable.headphone;
                }

                if (!title.isEmpty() && !artist.isEmpty() && !description.isEmpty()) {
                    podcasts.add(new Podcast(title, artist, description, coverImage, isAudiobook));
                    Toast.makeText(AddMedia.this,"Media Added!", Toast.LENGTH_SHORT).show();
                    Intent addedMediaPassback = new Intent(AddMedia.this, MainActivity.class);
                    addedMediaPassback.putExtra("podcast", podcasts);
                    setResult(RESULT_OK, addedMediaPassback);
                    ArrayList<Podcast> test = (ArrayList<Podcast>) addedMediaPassback.getSerializableExtra("podcast");
                    finish();
                } else {
                    Toast.makeText(AddMedia.this, "Please fill out the entry completely", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}
