package com.sudoku.android.mysudokuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DifficultySelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_select);

        /**
         *  TODO: Add put extras, for each difficulty button, the extra data being the different
         *  sudoku difficulty file.
         */
//        Button btn_easy = findViewById(R.id.btn_EasyDiff);
//        final Context context = getApplicationContext();
//        btn_easy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, PuzzleSelect.class);
//                startActivity(intent);
//            }
//        });
//
//        Button btn_medium = findViewById(R.id.btn_MediumDiff);
//        btn_medium.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, PuzzleSelect.class);
//                startActivity(intent);
//            }
//        });
//
//        Button btn_hard = findViewById(R.id.btn_HardDiff);
//        btn_hard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, PuzzleSelect.class);
//                startActivity(intent);
//            }
//        });

        Button btn_Easy = findViewById(R.id.btn_EasyDiff);

        btn_Easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PuzzleSelect.class);
                startActivity(intent);
            }
        });

        Button btn_Medium = findViewById(R.id.btn_MediumDiff);
        btn_Medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PuzzleSelect.class);
                startActivity(intent);
            }
        });

        Button btn_Hard = findViewById(R.id.btn_HardDiff);
        btn_Hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() ,PuzzleSelect.class);
                startActivity(intent);
            }
        });


    }

}
