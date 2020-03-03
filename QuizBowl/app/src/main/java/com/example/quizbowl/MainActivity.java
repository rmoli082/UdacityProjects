package com.example.quizbowl;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String name;
    String q2_answer = "";
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSubmitButtonClick(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        if (view.getId() == R.id.button_zero) {
            EditText et = findViewById(R.id.name);
            name = String.valueOf(et.getText());
            if (name.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter a name to continue", Toast.LENGTH_SHORT).show();
                return;
            }
            LinearLayout linlay = findViewById(R.id.welcome_screen);
            linlay.setVisibility(View.INVISIBLE);
            linlay = findViewById(R.id.question_one);
            linlay.setVisibility(View.VISIBLE);
            linlay = findViewById(R.id.name_bar);
            linlay.setVisibility(View.INVISIBLE);
        }
        TextView resultsHeader = findViewById(R.id.results_header);
        String resultsMessage = "Results for " + name;
        resultsHeader.setText(resultsMessage);
        if (view.getId() == R.id.button_one) {
            TextView tv = findViewById(R.id.first_question_answer);
            RadioGroup rg = findViewById(R.id.question_one_answers);
            RadioButton rb = findViewById(rg.getCheckedRadioButtonId());
            if (rb == null) {
                Toast.makeText(getApplicationContext(), "Please select an option.", Toast.LENGTH_SHORT).show();
                return;
            }
            LinearLayout linlay = findViewById(R.id.question_one);
            linlay.setVisibility(View.INVISIBLE);
            linlay = findViewById(R.id.question_two);
            linlay.setVisibility(View.VISIBLE);
            tv.setText(rb.getText());
            if (rg.getCheckedRadioButtonId() == R.id.q1_a3) {
                score++;
                tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_check_circle_outline_black_18dp,0,0,0);
                tv = findViewById(R.id.score);
                tv.setText(String.valueOf(score));
                Toast.makeText(getApplicationContext(), "Good job, " + name, Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(getApplicationContext(), "Keep at it, " + name, Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.button_two) {
            CheckBox q2a1 = findViewById(R.id.q2_a1);
            CheckBox q2a2 = findViewById(R.id.q2_a2);
            CheckBox q2a3 = findViewById(R.id.q2_a3);
            CheckBox q2a4 = findViewById(R.id.q2_a4);
            if (!q2a1.isChecked() && !q2a2.isChecked() && !q2a3.isChecked() && !q2a4.isChecked()) {
                Toast.makeText(getApplicationContext(), "Please select at least one option.", Toast.LENGTH_SHORT).show();
                return;
            }
            LinearLayout linlay = findViewById(R.id.question_two);
            linlay.setVisibility(View.INVISIBLE);
            linlay = findViewById(R.id.question_three);
            linlay.setVisibility(View.VISIBLE);
            onCheckboxClicked(findViewById(R.id.q2_a1));
            onCheckboxClicked(findViewById(R.id.q2_a2));
            onCheckboxClicked(findViewById(R.id.q2_a3));
            onCheckboxClicked(findViewById(R.id.q2_a4));
            TextView tv = findViewById(R.id.second_question_answer);
            tv.setText(q2_answer);
            if (q2a1.isChecked() && q2a2.isChecked() && !q2a3.isChecked() && q2a4.isChecked()) {
                tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_check_circle_outline_black_18dp,0,0,0);
                score++;
                tv = findViewById(R.id.score);
                tv.setText(String.valueOf(score));
                Toast.makeText(getApplicationContext(), "You're doing great, " + name, Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(getApplicationContext(), "That one was tough, " + name, Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.button_three) {
            TextView tv = findViewById(R.id.third_question_answer);
            RadioGroup rg = findViewById(R.id.question_three_answers);
            RadioButton rb = findViewById(rg.getCheckedRadioButtonId());
            if (rb == null) {
                Toast.makeText(getApplicationContext(), "Please select an option.", Toast.LENGTH_SHORT).show();
                return;
            }
            LinearLayout linlay = findViewById(R.id.question_three);
            linlay.setVisibility(View.INVISIBLE);
            linlay = findViewById(R.id.question_four);
            linlay.setVisibility(View.VISIBLE);
            tv.setText(rb.getText());
            if (rg.getCheckedRadioButtonId() == R.id.q3_a4) {
                tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_check_circle_outline_black_18dp, 0, 0, 0);
                score++;
                tv = findViewById(R.id.score);
                tv.setText(String.valueOf(score));
                Toast.makeText(getApplicationContext(), "Excellent job, " + name, Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getApplicationContext(), "Only one more to go, " + name, Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.button_four){
            EditText et2 = findViewById(R.id.year_answer);
            TextView tv = findViewById(R.id.fourth_question_answer);
            String q4_answer = String.valueOf(et2.getText());
            if (q4_answer.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter a year.", Toast.LENGTH_SHORT).show();
                return;
            }
            LinearLayout linlay =  findViewById(R.id.question_four);
            linlay.setVisibility(View.INVISIBLE);
            linlay = findViewById(R.id.results);
            linlay.setVisibility(View.VISIBLE);
            tv.setText(q4_answer);
            if (Integer.parseInt(q4_answer) == 1776) {
                tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_check_circle_outline_black_18dp,0,0,0);
                score++;
                tv = findViewById(R.id.score);
                tv.setText(String.valueOf(score));
            }
            linlay = findViewById(R.id.score_bar);
            linlay.setVisibility(View.VISIBLE);
            if (score > 2) {
                Toast.makeText(getApplicationContext(), "You got " + score + "/4! Great job " + name + "!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "You got " + score + "/4! Please try again...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        if (checked) {
            q2_answer += ((CheckBox) view).getText() + "\n";
        }
    }
}
