package com.example.android.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int teamAruns = 0;
    int teamAwalks = 0;
    int teamAhits = 0;
    int teamBruns = 0;
    int teamBwalks = 0;
    int teamBhits = 0;

    int inning = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTeamARunClick(View view) {
        teamAruns++;
        TextView t = findViewById(R.id.team_1_run_score);
        t.setText(String.valueOf(teamAruns));
    }

    public void onTeamAHitClick(View view) {
        teamAhits++;
        TextView t = findViewById(R.id.team_1_hit_total);
        t.setText(String.valueOf(teamAhits));
    }

    public void onTeamAWalkClick(View view) {
        teamAwalks++;
        TextView t = findViewById(R.id.team_1_walk_total);
        t.setText(String.valueOf(teamAwalks));
    }

    public void onTeamBRunClick(View view) {
        teamBruns++;
        TextView t = findViewById(R.id.team_2_run_score);
        t.setText(String.valueOf(teamBruns));
    }

    public void onTeamBHitClick(View view) {
        teamBhits++;
        TextView t = findViewById(R.id.team_2_hit_total);
        t.setText(String.valueOf(teamBhits));
    }

    public void onTeamBWalkClick(View view) {
        teamBwalks++;
        TextView t = findViewById(R.id.team_2_walk_total);
        t.setText(String.valueOf(teamBwalks));
    }

    public void onPrevInningClick(View view) {
        inning--;
        if (inning < 0) {
            inning = 0;
        }
        TextView t = findViewById(R.id.innings_total);
        t.setText(String.valueOf(inning));
        RadioButton rb = findViewById(R.id.radio_button_bottom);
        rb.toggle();
        RelativeLayout layout = findViewById(R.id.team_2);
        layout.setBackgroundColor(Color.parseColor("#b4b1b0"));
        layout = findViewById(R.id.team_1);
        layout.setBackgroundColor(Color.parseColor("#d4d2d2"));
    }

    public void onNextInningClick(View view) {
        inning++;
        TextView t = findViewById(R.id.innings_total);
        t.setText(String.valueOf(inning));
        RadioButton rb = findViewById(R.id.radio_button_top);
        rb.toggle();
        RelativeLayout layout = findViewById(R.id.team_1);
        layout.setBackgroundColor(Color.parseColor("#b4b1b0"));
        layout = findViewById(R.id.team_2);
        layout.setBackgroundColor(Color.parseColor("#d4d2d2"));
    }

    public void onHalfInningClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_button_top:
                if (checked) {
                    RelativeLayout layout = findViewById(R.id.team_1);
                    layout.setBackgroundColor(Color.parseColor("#b4b1b0"));
                    layout = findViewById(R.id.team_2);
                    layout.setBackgroundColor(Color.parseColor("#d4d2d2"));
                }
                break;
            case R.id.radio_button_bottom:
                if (checked) {
                    if (checked) {
                        RelativeLayout layout = findViewById(R.id.team_2);
                        layout.setBackgroundColor(Color.parseColor("#b4b1b0"));
                        layout = findViewById(R.id.team_1);
                        layout.setBackgroundColor(Color.parseColor("#d4d2d2"));
                    }
                    break;
                }
                break;
        }
    }

    public void onResetClick(View view) {
        teamAruns = 0;
        TextView t = findViewById(R.id.team_1_run_score);
        t.setText(String.valueOf(teamAruns));
        teamAwalks = 0;
        t = findViewById(R.id.team_1_walk_total);
        t.setText(String.valueOf(teamAwalks));
        teamAhits = 0;
        t = findViewById(R.id.team_1_hit_total);
        t.setText(String.valueOf(teamAhits));
        teamBruns = 0;
        t = findViewById(R.id.team_2_run_score);
        t.setText(String.valueOf(teamBruns));
        teamBwalks = 0;
        t = findViewById(R.id.team_2_walk_total);
        t.setText(String.valueOf(teamBwalks));
        teamBhits = 0;
        t = findViewById(R.id.team_2_hit_total);
        t.setText(String.valueOf(teamBhits));
        inning = 0;
        t = findViewById(R.id.innings_total);
        t.setText(String.valueOf(inning));

    }
}
