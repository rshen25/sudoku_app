package com.sudoku.android.mysudokuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Activity to select the difficulty of the Sudoku puzzle
 */
public class DifficultySelect extends AppCompatActivity {

    public static final String EXTRA_DIFFICULTY = "EXTRA_DIFFICULTY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_select);

        /**
         * Set up the buttons to open the puzzle select screen based on the difficulty the user selects
         */
        Button btn_Easy = findViewById(R.id.btn_EasyDiff);
        btn_Easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PuzzleSelect.class);
                intent.putExtra(EXTRA_DIFFICULTY, 0);
                startActivity(intent);
            }
        });

        Button btn_Medium = findViewById(R.id.btn_MediumDiff);
        btn_Medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PuzzleSelect.class);
                intent.putExtra(EXTRA_DIFFICULTY, 1);
                startActivity(intent);
            }
        });

        Button btn_Hard = findViewById(R.id.btn_HardDiff);
        btn_Hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() ,PuzzleSelect.class);
                intent.putExtra(EXTRA_DIFFICULTY, 2);
                startActivity(intent);
            }
        });


    }

}
